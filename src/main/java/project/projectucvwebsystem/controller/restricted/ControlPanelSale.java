package project.projectucvwebsystem.controller.restricted;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.service.EmployeeService;
import project.projectucvwebsystem.service.InvoiceService;
import project.projectucvwebsystem.service.JWTService;
import project.projectucvwebsystem.service.ProductService;
import project.projectucvwebsystem.service.SaleDetailsService;
import project.projectucvwebsystem.service.SaleService;
import project.projectucvwebsystem.service.UserService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelSale {
    
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SaleService saleService;

    @Autowired
    SaleDetailsService saleDetailsService;

    @Autowired
    JWTService jwtService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/extract-category") 
    public void ExtractProduct () {
        Cookie[] cookies = request.getCookies();
        String category = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("selectedCategory".equals(cookie.getName())) {
                    category = cookie.getValue();
                    break;
                }
            }
        }

        List<String> listProducts = productService.getNameLargeByCategory(category);
        String productsString = listProducts.stream()
        .map(product -> ""+product+"").collect(Collectors.joining("%c"));

        productsString = productsString.contains(" ")
        ? productsString.replace(" ", "%20")
        : productsString;

        Cookie productsCookie = new Cookie("ProductsString", productsString);
        productsCookie.setMaxAge(60*60);
        productsCookie.setPath("/restricted");
        //productsCookie.setSecure(true);

        response.addCookie(productsCookie);
        System.out.println(productsString);
    }

    @PostMapping("/add-product")
    public ResponseEntity<byte[]> addProduct (
        @RequestParam("category") String category,
        @RequestParam("product-name") String productName,
        @RequestParam("quantity") int quantity
    ) throws Exception {

        invoiceService.setCategory(category);
        invoiceService.addProduct(productName, quantity);
        System.out.println(invoiceService.viewProducts());

        String[] priceByProductList = saleService.showUniquePriceByProduct(quantity, productName);
        //System.out.println(quantity);
        //System.out.println(productName);
        
        StringBuilder strBuilder = new StringBuilder();

        for (String obj : priceByProductList) {
            
            if (obj.contains(",")) {
                obj.replaceAll(" ", "%80");
            }
            strBuilder.append(obj);
        }

        byte[] priceAndIGV = strBuilder.toString().getBytes();
        /*HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);*/
        //System.out.println("hoooooooo");
        //String priceByProduct = priceByProductList.stream().map(row -> row[0]+"%80"+row[1]).collect(Collectors.joining("%15"));

        /*priceByProduct = priceByProduct.contains(" ")
        ? priceByProduct.replace(" ", "%25")
        : priceByProduct;*/

        /*Cookie priceByProductCookieStored = new Cookie("PriceByProduct", priceByProduct);
        priceByProductCookieStored.setMaxAge(60*60);
        priceByProductCookieStored.setPath("/restricted");*/

        /*response.addCookie(priceByProductCookieStored);

        for (Object[] price : priceByProductList) {
            System.out.println(price[0]);
        }*/

        Cookie cookieSaleDetails = new Cookie("CookieSaleDetails", invoiceService.viewProducts());
        //cookieSaleDetails.setMaxAge(60*60);
        cookieSaleDetails.setPath("/restricted");

        response.addCookie(cookieSaleDetails);
        
        return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(priceAndIGV);
        
    }

    @PostMapping("/delete-only-one-product")
    public void RemoveOnlyOneProduct (@RequestParam("key-product") String keyProduct) {
        System.out.println("Removed: "+keyProduct);
        invoiceService.removeProduct(keyProduct);

        Cookie cookieSaleDetails = new Cookie("CookieSaleDetails", invoiceService.viewProducts());
        //cookieSaleDetails.setMaxAge(60*60);
        cookieSaleDetails.setPath("/restricted");

        response.addCookie(cookieSaleDetails);
    }

    @PostMapping("/register-sale")
    public String RegisterSale (
        @RequestParam("surname") String surname,
        @RequestParam("cel-client") String celClient
    ) {

        /*
         * The process of looking for an employee id
         */
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        String username = jwtService.extractUsername(jwt);
        int idUser = userService.FindIDByUsername(username);

        int idEmployee = employeeService.RetrieveEmployeeIDByUserID(idUser);

        /*
         * End: 'Process to looking for employee id'
         * *****************************************
         */

        int loops = 0;

        String idSales = saleService.InsertSaleAndReturnID(idEmployee, surname, celClient);
        List<Object[]> list = invoiceService.OverviewProducts();

        System.out.println(invoiceService.Size());
        System.out.println(invoiceService.viewProducts());
        while (loops < invoiceService.Size() && invoiceService.Size() != 0) {
            Object[] entry = list.get(loops);
            String key = (String) entry[0];
            System.out.println("key: "+key);
            int quantity = invoiceService.obtainQuantity(key);
            Long idProduct = productService.getProducIDByName(key);

            saleDetailsService.InsertSaleDetails(idSales, idProduct, quantity);
            productService.RemoveStockUnits(quantity, idProduct);
            loops++;
        }

        return "redirect:/restricted/control-panel";
    }

}

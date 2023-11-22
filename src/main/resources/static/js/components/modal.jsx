
const FormAddNewSupplier = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div className="modal fade" id="staticBackdropSupplier" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Nuevo Proveedor</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="/restricted/control-panel/register-supplier" method="POST">
              <div className="modal-body">
                <Input name="name" type="text" className="form-control" placeholder="Nombre" />
                <Input name="cel" type="text" className="form-control" placeholder="Número" />
                <Input name="email" type="text" className="form-control" placeholder="E-mail" />
                <Input name="address" type="text" className="form-control" placeholder="Dirección/Localidad" />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={closeModal}>Close</button>
                <button type="submit" className="btn btn-primary">Registrar</button>
              </div>
          </form>
        </div>
      </div>
    </div>
  );
}

const FormAddNewProduct = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div className="modal fade" id="staticBackdropProduct" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Nuevo Producto</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="/restricted/control-panel/register-product" method="POST">
              <div className="modal-body">
                <select name="category" className="form-select mb-3" aria-label="Default Select Value">
                  <option selected>Selecciona una categoría</option>
                  <option value="1">Porcelanato</option>
                  <option value="2">Inodoro</option>
                  <option value="3">Lavadero</option>
                  <option value="4">Accesorios</option>
                </select>
                <Input name="name" type="text" className="form-control" placeholder="Nombre Producto" />
                <Input 
                name="quantity" 
                type="number" 
                className="form-control" 
                placeholder="Cantidad" 
                defaultValue="0"
                min="0"
                max="1000"
                />
                <Input 
                name="unit-price" 
                type="number" 
                className="form-control" 
                placeholder="Precio Unitario" 
                defaultValue="0"
                min="0"
                step="0.5"
                />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={closeModal}>Close</button>
                <button type="submit" className="btn btn-primary">Registrar</button>
              </div>
          </form>
        </div>
      </div>
    </div>
  );
}
// form className="needs-validation"
const FormAddNewEmployee = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div
    className="modal fade" 
    id="staticBackdropEmployee" 
    data-bs-backdrop="static" 
    data-bs-keyboard="false" 
    tabIndex="-1" 
    aria-labelledby="staticBackdropLabel" 
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Nuevo Empleado</h5>
            <button 
            type="button" 
            className="btn-close" 
            data-bs-dismiss="modal" 
            aria-label="Close">
            </button>
          </div>
          <form 
          action="/restricted/control-panel/register-employee" 
          method="POST">
              <div className="modal-body">
                <div 
                class="accordion shadow p-2 mb-4 bg-body rounded" 
                id="accordionUser"
                >
                <div class="accordion-item">
                  <h2 class="accordion-header" id="headingOne">
                    <button 
                    class="accordion-button" 
                    type="button" 
                    data-bs-toggle="collapse" 
                    data-bs-target="#collapseOne" 
                    aria-expanded="true" 
                    aria-controls="collapseOne">
                      Usuario
                    </button>
                  </h2>
                  <div 
                  id="collapseOne" 
                  class="accordion-collapse collapse" 
                  aria-labelledby="headingOne" 
                  data-bs-parent="#accordionUser">
                    <div class="accordion-body">
                      <select name="roles" className="form-select mb-3" aria-label="Default Select Value">
                        <option defaultValue>Selecciona el Rol</option>
                        <option defaultValue="ADMIN">ADMIN</option>
                        <option defaultValue="WAREHOUSE_MANAGE">Encargado de Almacén</option>
                        <option defaultValue="VENDEDOR">Área de Ventas</option>
                      </select>
                      <Input name="username" type="text" className="form-control" placeholder="Username" />
                      <Input name="password" type="password" className="form-control" placeholder="Password" />
                    </div>
                  </div>
                </div>
                </div>
                <Input name="email" type="text" className="form-control" placeholder="E-mail" />
                <Input name="name" type="text" className="form-control" placeholder="Nombres" />
                <Input name="lastname" type="text" className="form-control" placeholder="Apellidos" />
                <Input name="address" type="text" className="form-control" placeholder="Dirección/Localidad" />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={closeModal}>Close</button>
                <button type="submit" className="btn btn-primary">Registrar</button>
              </div>
          </form>
        </div>
      </div>
    </div>
  );
}

const PreviewInvoice = ({setShowModal}) => {
  
  function closeModal () {setShowModal(false);}

  return (
    <div
    className="modal fade" 
    id="staticBackdropInvoice" 
    data-bs-backdrop="static" 
    data-bs-keyboard="false" 
    tabIndex="-1" 
    aria-labelledby="staticBackdropLabel" 
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Preview de la Factura</h5>
            <button 
            type="button" 
            className="btn-close" 
            data-bs-dismiss="modal" 
            aria-label="Close">
            </button>
          </div>
          <div className="modal-body">
              <h5
              className="p-3"
              >
                  Datos del Cliente
              </h5>
              <Input 
              name="surname" 
              type="text"  
              className="form-control"
              placeholder="Nombre completo"
              />
              <Input 
              name="cel-client"
              type="text"
              className="form-control"
              placeholder="Número de Contacto/Teléfono"
              />
            </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={closeModal}>Close</button>
            <button type="submit" className="btn btn-primary">Registrar</button>
          </div>
        </div>
      </div>
    </div>
  );
}
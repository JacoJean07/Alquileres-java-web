<%-- Document : pagos Created on : [fecha actual] Author : Pablo Perez --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="row">
    <div class="col-sm-12">
        <h1 class="text-center">Pagos</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Ingresar</h5>
                    <form action="../PagosServlet" class="col-sm-12 form-group" method="post">
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="monto">Monto</label>
                                <input type="number" step="0.01" class="form-control" id="monto" name="monto"
                                    placeholder="Monto" value="${dato != null ? dato.monto : ''}">
                                <input type="hidden" name="id" id="id"
                                    value="${dato != null ? dato.id : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="formaPago">Forma de Pago</label>
                                <select class="form-control" id="formaPago" name="formaPago">
                                    <option value="EFECTIVO" ${dato != null && dato.formaPago == 'EFECTIVO' ? 'selected' : ''}>Efectivo</option>
                                    <option value="TRANSFERENCIA" ${dato != null && dato.formaPago == 'TRANSFERENCIA' ? 'selected' : ''}>Transferencia</option>
                                    <option value="DEPOSITO" ${dato != null && dato.formaPago == 'DEPOSITO' ? 'selected' : ''}>Depósito</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="detallePago">Detalle de Pago</label>
                                <input type="text" class="form-control" id="detallePago" name="detallePago"
                                    placeholder="Detalle de Pago" value="${dato != null ? dato.detallePago : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="estado">Estado</label>
                                <select class="form-control" id="estado" name="estado">
                                    <option value="true" ${dato != null && dato.estado ? 'selected' : ''}>Activo</option>
                                    <option value="false" ${dato != null && !dato.estado ? 'selected' : ''}>Inactivo</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inquilino_id">Inquilino</label>
                            <select class="form-control" id="inquilino_id" name="inquilino_id">
                                <!-- Aquí deberías llenar los inquilinos desde el backend -->
                                <c:forEach items="${inquilinos}" var="inquilino">
                                    <option value="${inquilino.id}" ${dato != null && dato.inquilino_id == inquilino.id ? 'selected' : ''}>
                                        ${inquilino.nombres} ${inquilino.apellidos}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" name="accionInput"
                                value="${dato != null ? 'Editar' : 'Ingresar'}">
                            <a href="pagos.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- table section -->
<div class="row">
    <div class="col-sm-12">
        <h1 class="text-center">Registros de Pagos</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-header">
                    <form action="../PagosServlet" method="post">
                        <input type="submit" class="btn btn-primary" name="accionInput" value="mostrar">
                    </form>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha Registro</th>
                                <th>Monto</th>
                                <th>Forma de Pago</th>
                                <th>Detalle de Pago</th>
                                <th>Estado</th>
                                <th>Inquilino</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.lista}" var="pago">
                                <tr>
                                    <td>${pago.id}</td>
                                    <td>${pago.fechaRegistro}</td>
                                    <td>${pago.monto}</td>
                                    <td>${pago.formaPago}</td>
                                    <td>${pago.detallePago}</td>
                                    <td>${pago.estado ? 'Activo' : 'Inactivo'}</td>
                                    <td>${pago.inquilino.nombres} ${pago.inquilino.apellidos}</td>
                                    <td class="d-flex justify-content-center">
                                        <a href="../VistasServlet?vista=Pagos&id=${pago.id}">
                                            <button type="button" class="btn btn-primary">Editar</button>
                                        </a>
                                        <form action="../PagosServlet" method="post">
                                            <input type="hidden" name="id" id="id" value="${pago.id}">
                                            <input type="hidden" name="accionInput" id="accionInput" value="eliminar">
                                            <button class="btn btn-danger" type="submit"
                                                onclick="return confirm('¿Seguro que desea eliminar este pago?');">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp" />

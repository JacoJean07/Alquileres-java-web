<%-- Document : contratos Created on : [fecha actual] Author : Pablo Perez --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="row  content-container">
    <div class="col-sm-12">
        <h1 class="text-center">Contratos</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Ingresar</h5>
                    <form action="../ContratosServlet" class="col-sm-12 form-group" method="post">
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="valorMensual">Valor Mensual</label>
                                <input type="number" step="0.01" class="form-control" id="valorMensual" name="valorMensual"
                                    placeholder="Valor Mensual" value="${dato != null ? dato.valorMensual : ''}">
                                <input type="hidden" name="id" id="id" value="${dato != null ? dato.id : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="diaMaximoDePago">Día Máximo de Pago</label>
                                <input type="number" class="form-control" id="diaMaximoDePago" name="diaMaximoDePago"
                                    placeholder="Día Máximo de Pago" value="${dato != null ? dato.diaMaximoDePago : ''}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="fechaInicioContrato">Fecha Inicio</label>
                                <input type="datetime-local" class="form-control" id="fechaInicioContrato" name="fechaInicioContrato"
                                    value="${dato != null ? dato.fechaInicioContrato : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="fechaFinContrato">Fecha Fin</label>
                                <input type="datetime-local" class="form-control" id="fechaFinContrato" name="fechaFinContrato"
                                    value="${dato != null ? dato.fechaFinContrato : ''}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="estado">Estado</label>
                            <select class="form-control" id="estado" name="estado">
                                <option value="true" ${dato != null && dato.estado ? 'selected' : ''}>Activo</option>
                                <option value="false" ${dato != null && !dato.estado ? 'selected' : ''}>Inactivo</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="inquilino_id">Inquilino</label>
                            <select class="form-control" id="inquilino_id" name="inquilino_id">
                                <!-- Aquí deberías llenar los inquilinos desde el backend -->
                                <c:forEach items="${sessionScope.lista3}" var="inquilino">
                                    <option value="${inquilino.id}" ${dato != null && dato.inquilino_id == inquilino.id ? 'selected' : ''}>
                                        ${inquilino.nombres} ${inquilino.apellidos}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="propiedad_id">Propiedad</label>
                            <select class="form-control" id="propiedad_id" name="propiedad_id">
                                <!-- Aquí deberías llenar las propiedades desde el backend -->
                                <c:forEach items="${sessionScope.lista2}" var="propiedad">
                                    <option value="${propiedad.id}" ${dato != null && dato.propiedad_id == propiedad.id ? 'selected' : ''}>
                                        ${propiedad.direccion} (${propiedad.numero})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" name="accionInput"
                                value="${dato != null ? 'Editar' : 'Ingresar'}">
                            <a href="contratos.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- table section -->
<div class="row content-container">
    <div class="col-sm-12">
        <h1 class="text-center">Registros de Contratos</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-header">
                    <form action="../ContratosServlet" method="post">
                        <input type="submit" class="btn btn-primary" name="accionInput" value="mostrar">
                    </form>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Valor Mensual</th>
                                <th>Día Máximo de Pago</th>
                                <th>Estado</th>
                                <th>Inquilino</th>
                                <th>Propiedad</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.lista}" var="contrato">
                                <tr>
                                    <td>${contrato.id}</td>
                                    <td>${contrato.valorMensual}</td>
                                    <td>${contrato.diaMaximoDePago}</td>
                                    <td>${contrato.estado ? 'Activo' : 'Inactivo'}</td>
                                    <td>
                                        ${contrato.inquilino_id}
                                    </td>
                                    <td>${contrato.propiedad_id}</td>
                                    <td class="d-flex justify-content-center">
                                        <a href="../VistasServlet?vista=Contratos&id=${contrato.id}">
                                            <button type="button" class="btn btn-primary">Editar</button>
                                        </a>
                                        <form action="../ContratosServlet" method="post">
                                            <input type="hidden" name="id" id="id" value="${contrato.id}">
                                            <input type="hidden" name="accionInput" id="accionInput" value="eliminar">
                                            <button class="btn btn-danger" type="submit"
                                                onclick="return confirm('¿Seguro que desea eliminar este contrato?');">Eliminar</button>
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

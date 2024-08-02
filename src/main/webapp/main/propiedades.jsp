<%-- Document : propiedades Created on : [fecha actual] Author : Pablo Perez --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />

<div class="row">
    <div class="col-sm-12">
        <h1 class="text-center">Propiedades</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Ingresar</h5>
                    <form action="../PropiedadesServlet" class="col-sm-12 form-group" method="post">
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="direccion">Dirección</label>
                                <input type="text" class="form-control" id="direccion" name="direccion"
                                    placeholder="Dirección" value="${dato != null ? dato.direccion : ''}">
                                <input type="hidden" name="id" id="id"
                                    value="${dato != null ? dato.id : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="numero">Número</label>
                                <input type="text" class="form-control" id="numero" name="numero"
                                    placeholder="Número" value="${dato != null ? dato.numero : ''}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6">
                                <label for="descripcion">Descripción</label>
                                <input type="text" class="form-control" id="descripcion" name="descripcion"
                                    placeholder="Descripción" value="${dato != null ? dato.descripcion : ''}">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="estado">Estado</label>
                                <select class="form-control" id="estado" name="estado">
                                    <option value="DISPONIBLE" ${dato != null && dato.estado == 'DISPONIBLE' ? 'selected' : ''}>Disponible</option>
                                    <option value="OCUPADO" ${dato != null && dato.estado == 'OCUPADO' ? 'selected' : ''}>Ocupado</option>
                                    <option value="FUERA DE SERVICIO" ${dato != null && dato.estado == 'FUERA DE SERVICIO' ? 'selected' : ''}>Fuera de Servicio</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" name="accionInput"
                                value="${dato != null ? 'Editar' : 'Ingresar'}">
                            <a href="propiedades.jsp" class="btn btn-secondary">Cancelar</a>
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
        <h1 class="text-center">Registros de Propiedades</h1>
        <div class="card-deck">
            <div class="card">
                <div class="card-header">
                    <form action="../PropiedadesServlet" method="post">
                        <input type="submit" class="btn btn-primary" name="accionInput" value="mostrar">
                    </form>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Dirección</th>
                                <th>Número</th>
                                <th>Descripción</th>
                                <th>Estado</th>
                                <th>Propietario</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.lista}" var="propiedad">
                                <tr>
                                    <td>${propiedad.id}</td>
                                    <td>${propiedad.direccion}</td>
                                    <td>${propiedad.numero}</td>
                                    <td>${propiedad.descripcion}</td>
                                    <td>${propiedad.estado}</td>
                                    <td>${propiedad.propietario.nombre}</td>
                                    <td class="d-flex justify-content-center">
                                        <a href="../VistasServlet?vista=Propiedades&id=${propiedad.id}">
                                            <button type="button" class="btn btn-primary">Editar</button>
                                        </a>
                                        <form action="../PropiedadesServlet" method="post">
                                            <input type="hidden" name="id" id="id" value="${propiedad.id}">
                                            <input type="hidden" name="accionInput" id="accionInput" value="eliminar">
                                            <button class="btn btn-danger" type="submit"
                                                onclick="return confirm('¿Seguro que desea eliminar esta propiedad?');">Eliminar</button>
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

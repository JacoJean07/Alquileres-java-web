<%-- Document : sidebar Created on : 13 may. 2024, 16:18:26 Author : Pablo Perez --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


            <jsp:include page="includes/header.jsp" />

            <div class="row  content-container">
                <div class="col-sm-12">
                    <h1 class="text-center">Inquilinos</h1>
                    <div class="card-deck">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Ingresar</h5>
                                <form action="../InquilinosServlet" class="col-sm-12 form-group" method="post">
                                    <div class="row">
                                        <div class="form-group col-sm-6">
                                            <label for="nombres">Nombres</label>
                                            <input type="text" class="form-control" id="nombres" name="nombres"
                                                placeholder="Nombres" value="${dato != null ? dato.nombres : ''}">
                                            <input type="hidden" name="id" id="id"
                                                value="${dato != null ? dato.id : ''}">
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="apellidos">Apellidos</label>
                                            <input type="text" class="form-control" id="apellidos" name="apellidos"
                                                placeholder="Apellidos" value="${dato != null ? dato.apellidos : ''}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-sm-4">
                                            <label for="telefono">Telefono</label>
                                            <input type="text" class="form-control" id="telefono" name="telefono"
                                                placeholder="Telefono" value="${dato != null ? dato.telefono : ''}">
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <label for="correo">Correo</label>
                                            <input type="text" class="form-control" id="correo" name="correo"
                                                placeholder="Correo" value="${dato != null ? dato.correo : ''}">
                                        </div>

                                        <div class="form-group col-sm-4">
                                            <label for="cedula">Cedula</label>
                                            <input type="text" class="form-control" id="cedula" name="cedula"
                                                placeholder="Cedula" value="${dato != null ? dato.cedula : ''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" name="accionInput"
                                            value="${dato != null ? 'Editar' : 'Ingresar'}">
                                        <a href="inquilinos.jsp" class="btn btn-secondary">Cancelar</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- table section -->
            <div class="row  content-container">
                <div class="col-sm-12">
                    <h1 class="text-center">Registros de Inquilinos</h1>
                    <div class="card-deck">
                        <div class="card">
                            <div class="card-body">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombres</th>
                                            <th>Apellidos</th>
                                            <th>Cedula</th>
                                            <th>Telefono</th>
                                            <th>Correo</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.lista}" var="inquilino">
                                            <tr>
                                                <td>${inquilino.id}</td>
                                                <td>${inquilino.nombres}</td>
                                                <td>${inquilino.apellidos}</td>
                                                <td>${inquilino.cedula}</td>
                                                <td>${inquilino.telefono}</td>
                                                <td>${inquilino.correo}</td>
                                                <td class="d-flex justify-content-center">
                                                    <a href="../VistasServlet?vista=Inquilinos&id=${inquilino.id}">
                                                        <button type="button" class="btn btn-primary">Editar</button>
                                                    </a>
                                                    <form action="../InquilinosServlet" method="post">
                                                        <input type="hidden" name="id" id="id" value="${inquilino.id}">
                                                        <input type="hidden" name="accionInput" id="accionInput"
                                                            value="eliminar">
                                                        <button class="btn btn-danger" type="submit"
                                                            onclick="return confirm('¿Seguro que desea eliminar esta categoria?');">Eliminar</button>
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
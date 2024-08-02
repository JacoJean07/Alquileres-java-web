<%-- Document : footer Created on : 14 may. 2024, 13:51:02 Author : Pablo Perez --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="Sistema de gestión de alquileres" />
            <meta name="author" content="Pablo Perez" />
            <title>Panel de Alquileres</title>

            <!-- bootstrap 5.3 -->
            <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
            <link rel="stylesheet" href="../assets/index.css">
        </head>

        <body>
            <div class="wrapper ">
                <div class="main-panel">
                    <!-- Navbar -->
                    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
                        <div class="container-fluid">
                            <form action="../VistasServlet" method="POST">
                                <a class="navbar-brand" href="index.jsp">Dashboard</a>
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false"
                                    aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarColor01">
                                    <ul class="navbar-nav me-auto">
                                        <li class="nav-item">
                                            <input type="submit" name="vista"
                                                class="text-uppercase w-100 m2 nav-link bg-transparent"
                                                value="Inquilinos">
                                            <i class="fas fa-user mr-2 fa-2x"></i>
                                            </input>
                                        </li>
                                        <li class="nav-item">
                                            <input type="submit" name="vista"
                                                class="text-uppercase w-100 m2 nav-link bg-transparent"
                                                value="Propiedades">
                                            <i class="fas fa-cogs mr-2 fa-2x"></i>
                                            </input>
                                        </li>
                                        <li class="nav-item">
                                            <input type="submit" name="vista"
                                                class="text-uppercase w-100 m2 nav-link bg-transparent"
                                                value="Contratos">
                                            <i class=" fas fa-layer-group mr-2 fa-2x"></i>
                                            </input>
                                        </li>
                                        <li class="nav-item">
                                            <input type="submit" name="vista"
                                                class="text-uppercase w-100 m2 nav-link bg-transparent" value="Pagos">
                                            <i class="fab fa-product-hunt mr-2 fa-2x"></i>
                                            </input>
                                        </li>
                                        <li class="nav-item dropdown justify-content-end">
                                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
                                                role="button" aria-haspopup="true" aria-expanded="false">User</a>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="../index.jsp">Cerrar Sesión</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                        </div>
                    </nav>


                    <!-- End Navbar -->
                    <div class="content bg">
                        <div class="container-fluid">
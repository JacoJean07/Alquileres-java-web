<%@page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="includes/header.jsp" />

<div class="alert alert-dismissible alert-success mt-3 content-container">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    <h4 class="alert-heading">Bienvenido</h4>
    <p class="mb-0">
        Bienvenidos a la plataforma de Gestión de Alquileres. Aquí podrás administrar
        todas tus propiedades, inquilinos, contratos y pagos de manera eficiente. Utiliza
        el menú de navegación para acceder a las distintas secciones del sistema.
    </p>
</div>

<main class="container mt-5 d-flex justify-content-center content-container">
    <div class="row mx-4">
        <div class="d-flex justify-content-center">
            <div class="card text-white bg-primary mb-3" style="max-width: 20rem;">
                <div class="card-header">Gestiona tus propiedades eficientemente</div>
                <div class="card-body">
                    <h4 class="card-title">Primary card title</h4>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk
                        of the card's content.</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row mx-4">
        <div class="d-flex justify-content-center">
            <div class="card bg-secondary mb-3" style="max-width: 20rem;">
                <div class="card-header">Guarfa tus contratos de manera eficiente</div>
                <div class="card-body">
                    <h4 class="card-title">Secondary card title</h4>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk
                        of the card's content.</p>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="includes/footer.jsp" />

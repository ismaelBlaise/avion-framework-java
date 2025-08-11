<div class="flex flex-col items-center bg-gray-100 min-h-screen py-6">
    <!-- Conteneur du menu -->
    <div class="bg-white shadow-lg rounded-lg p-6 w-full max-w-md">
        <h2 class="text-lg font-semibold text-gray-700 mb-4 text-center">configurations</h2>
        <% String id=(String) request.getAttribute("id"); %>
        <!-- Boutons du menu -->
        <div class="flex flex-col space-y-3">
            <a href="vols-heure-reservation-form?id=<%=id%>">
                <button class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                    Fin reservation
                </button>
            </a>
            <a href="vols-heure-annulation-form?id=<%=id%>">
                <button class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                    Fin d'annulation
                </button>
            </a>
            <a href="vols-caracteristique-form?id=<%=id%>">
                <button class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                    Caracteristique du vol
                </button>
            </a>
            <a href="vols-promotion-form?id=<%=id%>">
                <button class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                    Promotions
                </button>
            </a>
        </div>
    </div>
</div>

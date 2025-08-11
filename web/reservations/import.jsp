<%@ page import="java.util.List" %>
<%@ page import="models.*" %>

<div class="py-6 max-w-md mx-auto bg-white rounded-lg shadow-md border border-gray-200">
    <h2 class="text-xl font-bold mb-4 text-center">Importer Passeport</h2>

    <%
        // On attend que l'id du detail de reservation soit passe en paramètre GET ou POST
        ReservationDetail detail = (ReservationDetail) request.getAttribute("detail");
        if (detail == null) {
            response.sendRedirect("reservations");
            return;
        }
       
    %>


    <% 
            String erreur = (String) request.getAttribute("erreur"); 
            String succes = (String) request.getAttribute("succes"); 

            
        %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        <% if (succes != null) { %>
            <div class="mb-4 p-3 text-green-700 bg-green-100 border border-green-400 rounded-lg text-sm">
                <%= succes%>
            </div>
        <% } %>
        

    <form method="post" action="import" enctype="multipart/form-data" class="flex flex-col gap-4 px-6 pb-6">

        <input type="hidden" name="id" value="<%= detail.getIdReservationDetail() %>" />

        <label for="passeportFile" class="block text-gray-700 font-semibold">
            Selectionnez le fichier passeport (PDF, JPG, PNG) :
        </label>
        <input type="file" id="passeportFile" name="passport" 
               accept=".pdf,.jpg,.jpeg,.png" required
               class="border border-gray-300 rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <button type="submit" 
                class="bg-blue-500 hover:bg-blue-600 text-white py-2 rounded-md transition-colors">
            Importer
        </button>
    </form>

    <div class="mt-4 text-center">
        <a href="reservation-details?id=<%= detail.getIdReservation() %>" 
           class="text-blue-500 hover:underline">
            Retour aux details de la reservation
        </a>
</div>

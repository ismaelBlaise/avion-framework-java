<%@ page import="models.Vol" %>
<%@ page import ="java.time.LocalDateTime"%>
<%@ page import ="java.time.format.DateTimeFormatter"%>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Ajouter limite reservation</h2>

    <!-- Formulaire de modification de vol -->
    <form action="vols-heure-reservation" method="POST" class="space-y-6">
        <% String erreur = (String) request.getAttribute("erreur"); %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>
        <% 
            Vol vol = (Vol) request.getAttribute("vol");
            LocalDateTime maintenant = LocalDateTime.now();
            DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateHeureFormatee = maintenant.format(formatteur);
        %>
        <input type="hidden" value="<%= vol.getIdVol() %>" id="id" name="id">

        

        
        <div>
            <label for="heureReservation" class="block text-gray-700 font-medium mb-2">Fin reservation</label>
            <input type="datetime" value="<%=dateHeureFormatee%>"  id="heureReservation" name="heureReservation" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>


        
        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter 
            </button>
        </div>
    </form>
</div>

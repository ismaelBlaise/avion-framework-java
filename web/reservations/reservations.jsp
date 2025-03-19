<%@ page import ="java.util.*"%>
<%@ page import ="models.*"%>
<% 
            String erreur = (String) request.getAttribute("erreur"); 
            List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
            List<Classe> classes = (List<Classe>) request.getAttribute("classes");
            Vol vol=(Vol) request.getAttribute("vol");
%>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Creer une reservation pour le vol <strong><%=vol.getNumero()%></strong></h2>

    <!-- Formulaire d'ajout de reservation -->
    <form action="reservations-ajouter" method="POST" class="space-y-6">
        
        
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        
        <div>
            <label for="dateReservation" class="block text-gray-700 font-medium mb-2">Date de reservation</label>
            <input type="datetime-local" id="dateReservation" name="reservation.dateReservation" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        
        <div>
            <label for="idStatut" class="block text-gray-700 font-medium mb-2">Statut</label>
            <select id="idStatut" name="reservation.idStatut" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Statut statut : statuts) { %>
                    <option value="<%= statut.getIdStatut() %>"><%= statut.getStatut() %></option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="idClasse" class="block text-gray-700 font-medium mb-2">Classe</label>
            <select id="idClasse" name="reservation.idClasse" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Classe classe : classes) { %>
                    <option value="<%= classe.getIdClasse() %>"><%= classe.getClasse() %></option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="idVol" class="block text-gray-700 font-medium mb-2">Vol</label>
            <input type="text" id="idVol" value="<%= vol.getNumero() %>" 
                disabled class="w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-100">
            <input type="hidden" name="reservation.idVol" value="<%= vol.getIdVol() %>">
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Creer la reservation
            </button>
        </div>
    </form>
</div>

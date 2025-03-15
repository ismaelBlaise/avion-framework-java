<%@ page import="models.*" %>
<%@ page import="java.util.*" %>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Ajouter une promotion</h2>
    <form action="vols-promotion-ajouter" method="POST" class="space-y-6">
        <% 
            String erreur = (String) request.getAttribute("erreur"); 
            Vol vol=(Vol) request.getAttribute("vol");
        %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        <div>
            <label for="idVol" class="block text-gray-700 font-medium mb-2">Vol</label>
            <input type="text" id="idVol" value="<%= vol.getNumero() %>" 
                disabled class="w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-100">
            <input type="hidden" name="promotion.idVol" value="<%= vol.getIdVol() %>">
        </div>

        <div>
            <label for="idClasse" class="block text-gray-700 font-medium mb-2">Classe</label>
            <select id="idClasse" name="promotion.idClasse" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% 
                    List<Classe> classes = (List<Classe>) request.getAttribute("classes");
                    for (Classe classe : classes) { 
                %>
                    <option value="<%= classe.getIdClasse() %>"><%= classe.getClasse() %></option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="pourcentage" class="block text-gray-700 font-medium mb-2">Pourcentage de promotion</label>
            <input type="number" id="pourcentage" value="1" name="promotion.pourcentage" required min="0" max="100" step="0.01"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="nbSiege" class="block text-gray-700 font-medium mb-2">Nombre de sieges disponibles</label>
            <input type="number" id="nbSiege" value="1" name="promotion.nbSieze" required min="0" step="1"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter la promotion
            </button>
        </div>
    </form>
</div>

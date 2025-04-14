<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<%
    String erreur = (String) request.getAttribute("erreur");
    String succes = (String) request.getAttribute("succes");
    int reservation = (int) request.getAttribute("reservation");
    List<CategorieAge> categoriesAge = (List<CategorieAge>) request.getAttribute("categoriesAge");
    Classe classe=(Classe) request.getAttribute("classe");
%>

<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">
        Ajouter des details pour la reservation #<strong><%= reservation%></strong>
    </h2>

    <!-- Formulaire d'ajout de details de reservation -->
    <form action="vols-reservation-details" method="POST" class="space-y-6">

        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        <% if (succes != null) { %>
            <div class="mb-4 p-3 text-green-700 bg-red-100 border border-green-400 rounded-lg text-sm">
                <%= succes %>
            </div>
        <% } %>

        <input type="hidden" name="idReservation" value="<%= reservation %>">
        <div>
            <label for="idVol" class="block text-gray-700 font-medium mb-2">Classe</label>
            <input type="text" id="idVol" value="<%= classe.getClasse() %>" 
                disabled class="w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-100">
            <input type="hidden" name="idClasse" value="<%= classe.getIdClasse() %>">
        </div>
        <div>
            <label for="idCategorieAge" class="block text-gray-700 font-medium mb-2">Categorie d'age</label>
            <select id="idCategorieAge" name="idCategorieAge" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (CategorieAge categorie : categoriesAge) { %>
                    <option value="<%= categorie.getIdCategorieAge() %>"><%= categorie.getCategorie() %></option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="nb" class="block text-gray-700 font-medium mb-2">Nombre de places</label>
            <input type="number" id="nb" name="nb" min="1" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter le detail
            </button>
        </div>
    </form>
</div>

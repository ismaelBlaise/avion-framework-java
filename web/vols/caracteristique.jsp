
<%@ page import="models.*" %>
<%@ page import="java.util.*" %>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Ajouter une caracteristique de vol</h2>

    <!-- Formulaire d'ajout de caracteristique -->
    <form action="vols-caracteristique-ajouter" method="POST" class="space-y-6">
        <% 
            String erreur = (String) request.getAttribute("erreur"); 
            Vol vol=(Vol) request.getAttribute("vol");
        %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        <!-- Champ Vol (Desactive) -->
        <div>
            <label for="idVol" class="block text-gray-700 font-medium mb-2">Vol</label>
            <input type="text" id="idVol" value="<%= vol.getNumero() %>" 
                disabled class="w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-100">
            <input type="hidden" name="conf_vol.idVol" value="<%= vol.getIdVol() %>">
        </div>

        <!-- Champ Classe -->
        <div>
            <label for="idClasse" class="block text-gray-700 font-medium mb-2">Classe</label>
            <select id="idClasse" name="conf_vol.idClasse" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% 
                    List<Classe> classes = (List<Classe>) request.getAttribute("classes");
                    for (Classe classe : classes) { 
                %>
                    <option value="<%= classe.getIdClasse() %>"><%= classe.getClasse() %></option>
                <% } %>
            </select>
        </div>

        <!-- Champ Categorie d'âge -->
        <div>
            <label for="idCategorieAge" class="block text-gray-700 font-medium mb-2">Categorie d'age</label>
            <select id="idCategorieAge" name="conf_vol.idCategorieAge" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% 
                    List<CategorieAge> categoriesAge = (List<CategorieAge>) request.getAttribute("categories-age");
                    for (CategorieAge categorie : categoriesAge) { 
                %>
                    <option value="<%= categorie.getIdCategorieAge() %>">
                        <%= categorie.getCategorie() %> (<%= categorie.getAgeMin() %> - <%= categorie.getAgeMax() %> ans)
                    </option>
                <% } %>
            </select>
        </div>

        <!-- Champ Montant -->
        <div>
            <label for="montant" class="block text-gray-700 font-medium mb-2">Montant</label>
            <input type="number" id="montant" name="conf_vol.montant" required min="0" step="0.001"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Capacite -->
        <div>
            <label for="capacite" class="block text-gray-700 font-medium mb-2">Capacite</label>
            <input type="number" id="capacite" name="conf_vol.capacite" required min="0"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter la caracteristique
            </button>
        </div>
    </form>
</div>

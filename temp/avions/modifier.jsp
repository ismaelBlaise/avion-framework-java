<%@ page import="models.Avion" %>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Modifier l'avion</h2>

    <!-- Formulaire d'ajout d'avion -->
    <form action="avions-update" method="POST" class="space-y-6">
        <% String erreur = (String) request.getAttribute("erreur"); %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>
        <% 
            Avion avion=(Avion) request.getAttribute("avion");
        %>
        <!-- Champ Modele -->
        <div>
            <label for="modele" class="block text-gray-700 font-medium mb-2">Modele</label>
            <input type="text" value="<%= avion.getModele() %>" id="modele" name="avion.modele" required placeholder="Entrer le modele de l'avion"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Capacite -->
        <div>
            <label for="capacite" class="block text-gray-700 font-medium mb-2">Capacite</label>
            <input type="number" value="<%=avion.getCapacite()%>" id="capacite" name="avion.capacite" required placeholder="Entrer la capacite de l'avion"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Modifier l'avion
            </button>
        </div>
    </form>
</div>

<%@ page import ="java.util.*"%>
<%@ page import ="models.*"%>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Ajouter un nouveau vol</h2>

    <!-- Formulaire d'ajout de vol -->
    <form action="vols-ajouter" method="POST" class="space-y-6">
        <% 
            String erreur = (String) request.getAttribute("erreur"); 
            List<Ville> villes = (List<Ville>) request.getAttribute("villes");
            List<Avion> avions = (List<Avion>) request.getAttribute("avions");
            List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");

        %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>

        <!-- Champ Numero -->
        <div>
            <label for="numero" class="block text-gray-700 font-medium mb-2">Numero de vol</label>
            <input type="text" id="numero" name="vol.numero" required placeholder="Entrer le numero du vol"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Date -->
        <div>
            <label for="dateVol" class="block text-gray-700 font-medium mb-2">Date du vol</label>
            <input type="date" id="dateVol" name="vol.dateVol" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Heure de depart -->
        <div>
            <label for="heureDepart" class="block text-gray-700 font-medium mb-2">Heure de depart</label>
            <input type="time" id="heureDepart" name="vol.heureDepart" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Heure d'arrivee -->
        <div>
            <label for="heureArrive" class="block text-gray-700 font-medium mb-2">Heure d'arrivee</label>
            <input type="time" id="heureArrive" name="vol.heureArrive" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        
        <!-- Champ Ville de depart -->
        <div>
            <label for="idVilleDepart" class="block text-gray-700 font-medium mb-2">Ville de depart</label>
            <select id="idVilleDepart" name="vol.idVilleDepart" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Ville ville : villes) { %>
                    <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                <% } %>
            </select>
        </div>

        <!-- Champ Ville d'arrivee -->
        <div>
            <label for="idVilleArrive" class="block text-gray-700 font-medium mb-2">Ville d'arrivee</label>
            <select id="idVilleArrive" name="vol.idVilleArrive" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Ville ville : villes) { %>
                    <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                <% } %>
            </select>
        </div>
        <div>
            <label for="idStatut" class="block text-gray-700 font-medium mb-2">Statut</label>
            <select id="idStatut" name="vol.idStatut" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Statut statut : statuts) { %>
                    <option value="<%= statut.getIdStatut() %>" >
                        <%= statut.getStatut() %>
                    </option>
                <% } %>
            </select>
        </div>
        <!-- Champ Avion -->
        <div>
            <label for="idAvion" class="block text-gray-700 font-medium mb-2">Avion</label>
            <select id="idAvion" name="vol.idAvion" required 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <% for (Avion avion : avions) { %>
                    <option value="<%= avion.getIdAvion() %>"><%= avion.getModele() %></option>
                <% } %>
            </select>
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter le vol
            </button>
        </div>
    </form>
</div>

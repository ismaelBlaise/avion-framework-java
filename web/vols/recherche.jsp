<%@ page import ="java.util.*"%>
<%@ page import ="models.*"%>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Espace de recherche</h2>

    <!-- Formulaire de recherche de vol -->
    <form action="vols-rechercher" method="POST" class="space-y-6">
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
            <input type="text" id="numero" name="recherche.numero" placeholder="Entrer le numero du vol"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Date de vol (Intervalle) -->
        <div class="flex space-x-4">
            <div>
                <label for="dateDebut" class="block text-gray-700 font-medium mb-2">Date de vol - Debut</label>
                <input type="date" id="dateDebut" name="recherche.dateDebut" 
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div>
                <label for="dateFin" class="block text-gray-700 font-medium mb-2">Date de vol - Fin</label>
                <input type="date" id="dateFin" name="recherche.dateFin" 
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
        </div>

        <!-- Champ Heure de depart (Intervalle) -->
        <div class="flex space-x-4">
            <div>
                <label for="heureDepartDebut" class="block text-gray-700 font-medium mb-2">Heure de depart - Debut</label>
                <input type="time" id="heureDepartDebut" name="recherche.heureDebut" 
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div>
                <label for="heureDepartFin" class="block text-gray-700 font-medium mb-2">Heure de depart - Fin</label>
                <input type="time" id="heureDepartFin" name="recherche.heureFin" 
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
        </div>

        <!-- Champ Ville de depart -->
        <div>
            <label for="idVilleDepart" class="block text-gray-700 font-medium mb-2">Ville de depart</label>
            <select id="idVilleDepart" name="recherche.idVilleDepart" 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="">Toutes les villes</option>
                <% for (Ville ville : villes) { %>
                    <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                <% } %>
            </select>
        </div>

        <!-- Champ Ville d'arrivee -->
        <div>
            <label for="idVilleArrive" class="block text-gray-700 font-medium mb-2">Ville d'arrivee</label>
            <select id="idVilleArrive" name="recherche.idVilleArrive" 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="">Toutes les villes</option>
                <% for (Ville ville : villes) { %>
                    <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                <% } %>
            </select>
        </div>

        <!-- Champ Statut -->
        <div>
            <label for="idStatut" class="block text-gray-700 font-medium mb-2">Statut</label>
            <select id="idStatut" name="recherche.idStatut" 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="">Tous les statuts</option>
                <% for (Statut statut : statuts) { %>
                    <option value="<%= statut.getIdStatut() %>"><%= statut.getStatut() %></option>
                <% } %>
            </select>
        </div>

        <!-- Champ Avion -->
        <div>
            <label for="idAvion" class="block text-gray-700 font-medium mb-2">Avion</label>
            <select id="idAvion" name="recherche.idAvion" 
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="">Tous les avions</option>
                <% for (Avion avion : avions) { %>
                    <option value="<%= avion.getIdAvion() %>"><%= avion.getModele() %></option>
                <% } %>
            </select>
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Rechercher les vols
            </button>
        </div>
    </form>
</div>

<%@ page import="models.Vol" %>
<div class="max-w-4xl mx-auto py-8">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Modifier le vol</h2>

    <!-- Formulaire de modification de vol -->
    <form action="vols-update" method="POST" class="space-y-6">
        <% String erreur = (String) request.getAttribute("erreur"); %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>
        <% 
            Vol vol = (Vol) request.getAttribute("vol");
        %>
        <!-- Champ Numero -->
        <div>
            <label for="numero" class="block text-gray-700 font-medium mb-2">Numero</label>
            <input type="text" value="<%= vol.getNumero() %>" id="numero" name="vol.numero" required placeholder="Entrer le numero du vol"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Date du Vol -->
        <div>
            <label for="dateVol" class="block text-gray-700 font-medium mb-2">Date du vol</label>
            <input type="date" value="<%= vol.getDateVol() %>" id="dateVol" name="vol.dateVol" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Heure de Depart -->
        <div>
            <label for="heureDepart" class="block text-gray-700 font-medium mb-2">Heure de depart</label>
            <input type="time" value="<%= vol.getHeureDepart() %>" id="heureDepart" name="vol.heureDepart" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Heure d'Arrivee -->
        <div>
            <label for="heureArrive" class="block text-gray-700 font-medium mb-2">Heure d'arrivee</label>
            <input type="time" value="<%= vol.getHeureArrive() %>" id="heureArrive" name="vol.heureArrive" required
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Statut (idStatut) -->
        <div>
            <label for="idStatut" class="block text-gray-700 font-medium mb-2">Statut</label>
            <input type="number" value="<%= vol.getIdStatut() %>" id="idStatut" name="vol.idStatut" required placeholder="Entrer le statut du vol"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Ville de Depart (idVilleDepart) -->
        <div>
            <label for="idVilleDepart" class="block text-gray-700 font-medium mb-2">Ville de depart</label>
            <input type="number" value="<%= vol.getIdVilleDepart() %>" id="idVilleDepart" name="vol.idVilleDepart" required placeholder="Entrer l'ID de la ville de depart"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ Ville d'Arrivee (idVilleArrive) -->
        <div>
            <label for="idVilleArrive" class="block text-gray-700 font-medium mb-2">Ville d'arrivee</label>
            <input type="number" value="<%= vol.getIdVilleArrive() %>" id="idVilleArrive" name="vol.idVilleArrive" required placeholder="Entrer l'ID de la ville d'arrivee"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Champ ID Avion -->
        <div>
            <label for="idAvion" class="block text-gray-700 font-medium mb-2">ID Avion</label>
            <input type="number" value="<%= vol.getIdAvion() %>" id="idAvion" name="vol.idAvion" required placeholder="Entrer l'ID de l'avion"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <!-- Bouton Soumettre -->
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Modifier le vol
            </button>
        </div>
    </form>
</div>

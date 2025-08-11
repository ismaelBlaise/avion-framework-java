<%@ page import ="java.util.*"%>
<%@ page import ="models.*"%>
<%@ page import ="java.time.LocalDateTime"%>
<%@ page import ="java.time.format.DateTimeFormatter"%>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Titre avec icône -->
        <div class="flex items-center mb-6">
            <i data-lucide="plane" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Ajouter un nouveau vol</h2>
        </div>

        <!-- Formulaire d'ajout de vol -->
        <form action="vols-ajouter" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur"); 
                List<Ville> villes = (List<Ville>) request.getAttribute("villes");
                List<Avion> avions = (List<Avion>) request.getAttribute("avions");
                List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
                LocalDateTime maintenant = LocalDateTime.now();
                DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateHeureFormatee = maintenant.format(formatteur);
            %>
            
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <!-- Champ Numero de vol -->
            <div class="space-y-2">
                <label for="numero" class="block text-sm font-medium text-gray-700">Numero de vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="hash" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" id="numero" name="vol.numero" required placeholder="Entrer le numero du vol"
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Champ Date/Heure de depart -->
            <div class="space-y-2">
                <label for="heureDepart" class="block text-sm font-medium text-gray-700">Depart</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="calendar" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%=dateHeureFormatee%>" id="heureDepart" name="vol.depart" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Champ Date/Heure d'arrivee -->
            <div class="space-y-2">
                <label for="heureArrive" class="block text-sm font-medium text-gray-700">Arrivee</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="calendar" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%=dateHeureFormatee%>" id="heureArrive" name="vol.arrive" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Ville de depart -->
            <div class="space-y-2">
                <label for="idVilleDepart" class="block text-sm font-medium text-gray-700">Ville de depart</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="map-pin" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idVilleDepart" name="vol.idVilleDepart" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (Ville ville : villes) { %>
                            <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Ville d'arrivee -->
            <div class="space-y-2">
                <label for="idVilleArrive" class="block text-sm font-medium text-gray-700">Ville d'arrivee</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="map-pin" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idVilleArrive" name="vol.idVilleArrive" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (Ville ville : villes) { %>
                            <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Statut -->
            <div class="space-y-2">
                <label for="idStatut" class="block text-sm font-medium text-gray-700">Statut</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="flag" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idStatut" name="vol.idStatut" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (Statut statut : statuts) { %>
                            <option value="<%= statut.getIdStatut() %>">
                                <%= statut.getStatut() %>
                            </option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Avion -->
            <div class="space-y-2">
                <label for="idAvion" class="block text-sm font-medium text-gray-700">Avion</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="airplane" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idAvion" name="vol.idAvion" required 
                        class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (Avion avion : avions) { %>
                            <option value="<%= avion.getIdAvion() %>"><%= avion.getModele() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Boutons -->
            <div class="flex justify-end space-x-4 pt-4">
                <a href="vols" class="inline-flex items-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50">
                    <i data-lucide="arrow-left" class="w-4 h-4 mr-2"></i>
                    Annuler
                </a>
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                    Ajouter le vol
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    // Initialisation des icônes Lucide
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
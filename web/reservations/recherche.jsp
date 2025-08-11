<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Header with icon -->
        <div class="flex items-center mb-6">
            <i data-lucide="search" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Recherche de vols</h2>
        </div>

        <!-- Search form -->
        <form action="vols-rechercher-front" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur"); 
                List<Ville> villes = (List<Ville>) request.getAttribute("villes");
                List<Avion> avions = (List<Avion>) request.getAttribute("avions");
                List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
            %>
            
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <!-- Flight number -->
            <div class="space-y-2">
                <label for="numero" class="block text-sm font-medium text-gray-700">Numero de vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="hash" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" id="numero" name="recherche.numero" placeholder="Entrer le numero du vol"
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Date range -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div class="space-y-2">
                    <label for="dateDebut" class="block text-sm font-medium text-gray-700">Date de debut</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="calendar" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <input type="date" id="dateDebut" name="recherche.dateDebut"
                               class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                    </div>
                </div>
                <div class="space-y-2">
                    <label for="dateFin" class="block text-sm font-medium text-gray-700">Date de fin</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="calendar" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <input type="date" id="dateFin" name="recherche.dateFin"
                               class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                    </div>
                </div>
            </div>

            <!-- Departure/Arrival cities -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div class="space-y-2">
                    <label for="idVilleDepart" class="block text-sm font-medium text-gray-700">Ville de depart</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="map-pin" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idVilleDepart" name="recherche.villeDepart"
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <option value="">Toutes les villes</option>
                            <% for (Ville ville : villes) { %>
                                <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="space-y-2">
                    <label for="idVilleArrive" class="block text-sm font-medium text-gray-700">Ville d'arrivee</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="map-pin" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idVilleArrive" name="recherche.villeArrive"
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <option value="">Toutes les villes</option>
                            <% for (Ville ville : villes) { %>
                                <option value="<%= ville.getIdVille() %>"><%= ville.getVille() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
            </div>

            <!-- Status and Aircraft -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div class="space-y-2">
                    <label for="idStatut" class="block text-sm font-medium text-gray-700">Statut</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="flag" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idStatut" name="recherche.idStatut"
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <option value="">Tous les statuts</option>
                            <% for (Statut statut : statuts) { %>
                                <option value="<%= statut.getIdStatut() %>"><%= statut.getStatut() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="space-y-2">
                    <label for="idAvion" class="block text-sm font-medium text-gray-700">Avion</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="airplane" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idAvion" name="recherche.idAvion"
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <option value="">Tous les avions</option>
                            <% for (Avion avion : avions) { %>
                                <option value="<%= avion.getIdAvion() %>"><%= avion.getModele() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
            </div>

            <!-- Submit button -->
            <div class="flex justify-center pt-4">
                <button type="submit" class="inline-flex items-center px-6 py-3 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="search" class="w-5 h-5 mr-2"></i>
                    Rechercher les vols
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
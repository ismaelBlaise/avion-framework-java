<%@ page import="models.*" %>
<%@ page import="java.util.*" %>

<div class="max-w-6xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Titre avec icône -->
        <div class="flex items-center mb-6">
            <i data-lucide="settings" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Configuration des caracteristiques de vol</h2>
        </div>

        <!-- Formulaire d'ajout -->
        <form action="vols-caracteristique-ajouter" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur"); 
                String succes = (String) request.getAttribute("succes");
                Vol vol = (Vol) request.getAttribute("vol");
            %>
            
            <!-- Messages d'erreur/succès -->
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <% if (succes != null) { %>
                <div class="p-4 text-green-700 bg-green-50 border-l-4 border-green-500 rounded-md flex items-start">
                    <i data-lucide="check-circle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= succes %></div>
                </div>
            <% } %>

            <!-- Champ Vol (desactive) -->
            <div class="space-y-2">
                <label class="block text-sm font-medium text-gray-700">Vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="plane" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%= vol.getNumero() %>" 
                           disabled class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-50">
                    <input type="hidden" name="conf_vol.idVol" value="<%= vol.getIdVol() %>">
                </div>
            </div>

            <!-- Grille de champs -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- Classe -->
                <div class="space-y-2">
                    <label for="idClasse" class="block text-sm font-medium text-gray-700">Classe</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="layers" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idClasse" name="conf_vol.idClasse" required 
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <% 
                                List<Classe> classes = (List<Classe>) request.getAttribute("classes");
                                for (Classe classe : classes) { 
                            %>
                                <option value="<%= classe.getIdClasse() %>"><%= classe.getClasse() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <!-- Categorie d'age -->
                <div class="space-y-2">
                    <label for="idCategorieAge" class="block text-sm font-medium text-gray-700">Categorie d'age</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="users" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <select id="idCategorieAge" name="conf_vol.idCategorieAge" required 
                                class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <% 
                                List<CategorieAge> categoriesAge = (List<CategorieAge>) request.getAttribute("categories-age");
                                for (CategorieAge categorie : categoriesAge) { 
                            %>
                                <option value="<%= categorie.getIdCategorieAge() %>">
                                    <%= categorie.getCategorie() %> (<%= categorie.getAgeMin() %>-<%= categorie.getAgeMax() %> ans)
                                </option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <!-- Montant -->
                <div class="space-y-2">
                    <label for="montant" class="block text-sm font-medium text-gray-700">Montant (MGA)</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="dollar-sign" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <input type="number" id="montant" name="conf_vol.montant" required min="0" step="0.001"
                               class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                    </div>
                </div>

                <!-- Capacite -->
                <div class="space-y-2">
                    <label for="capacite" class="block text-sm font-medium text-gray-700">Capacite</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <i data-lucide="user-plus" class="w-5 h-5 text-gray-400"></i>
                        </div>
                        <input type="number" id="capacite" name="conf_vol.capacite" required min="0"
                               class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                    </div>
                </div>
            </div>

            <!-- Bouton de soumission -->
            <div class="flex justify-end pt-4">
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                    Ajouter la configuration
                </button>
            </div>
        </form>

        <!-- Liste des configurations existantes -->
        <div class="mt-12">
            <h3 class="text-xl font-semibold text-gray-800 mb-4 flex items-center">
                <i data-lucide="list" class="w-5 h-5 mr-2 text-blue-500"></i>
                Configurations existantes
            </h3>
            
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Classe</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Categorie d'age</th>
                            <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Montant</th>
                            <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Capacite</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <% 
                            List<ConfVol> confVols = (List<ConfVol>) request.getAttribute("conf-vols");
                            if (confVols != null && !confVols.isEmpty()) {
                                for (ConfVol conf : confVols) {
                        %>
                            <tr class="hover:bg-gray-50">
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900"><%= conf.getNomClasse() %></td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><%= conf.getCategorieAge() %></td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-right"><%= String.format("%.3f", conf.getMontant()) %> MGA</td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-right"><%= conf.getCapacite() %></td>
                            </tr>
                        <%  
                                }
                            } else { 
                        %>
                            <tr>
                                <td colspan="4" class="px-6 py-8 text-center">
                                    <div class="flex flex-col items-center justify-center text-gray-400">
                                        <i data-lucide="alert-circle" class="w-12 h-12 mb-3"></i>
                                        <p class="text-lg font-medium">Aucune configuration enregistree</p>
                                        <p class="text-sm mt-1">Ajoutez une configuration ci-dessus</p>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    // Initialisation des icônes Lucide
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
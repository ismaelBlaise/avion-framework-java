<%@ page import="models.*" %>
<%@ page import="java.util.*" %>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Header with icon -->
        <div class="flex items-center mb-6">
            <i data-lucide="tag" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Ajouter une promotion</h2>
        </div>

        <!-- Form -->
        <form action="vols-promotion-ajouter" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur"); 
                String succes = (String) request.getAttribute("succes");
                Vol vol = (Vol) request.getAttribute("vol");
            %>
            
            <!-- Error/Success messages -->
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

            <!-- Flight info (readonly) -->
            <div class="space-y-2">
                <label class="block text-sm font-medium text-gray-700">Vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="plane" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%= vol.getNumero() %>" disabled
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-50">
                    <input type="hidden" name="promotion.idVol" value="<%= vol.getIdVol() %>">
                </div>
            </div>

            <!-- Class selection -->
            <div class="space-y-2">
                <label for="idClasse" class="block text-sm font-medium text-gray-700">Classe</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="layers" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idClasse" name="promotion.idClasse" required 
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

            <!-- Percentage input -->
            <div class="space-y-2">
                <label for="pourcentage" class="block text-sm font-medium text-gray-700">Pourcentage de reduction</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="percent" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="number" id="pourcentage" value="1" name="promotion.pourcentage" 
                           required min="0" max="100" step="0.01"
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
                <p class="mt-1 text-sm text-gray-500">Entrez un pourcentage entre 0 et 100</p>
            </div>

            <!-- Seats input -->
            <div class="space-y-2">
                <label for="nbSiege" class="block text-sm font-medium text-gray-700">Nombre de sieges</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="user" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="number" id="nbSiege" value="1" name="promotion.nbSieze" 
                           required min="0" step="1"
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
                <p class="mt-1 text-sm text-gray-500">Nombre de sieges disponibles avec cette promotion</p>
            </div>

            <!-- Submit button -->
            <div class="flex justify-end pt-4">
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                    Ajouter la promotion
                </button>
            </div>
        </form>

        <!-- Existing promotions -->
        <% 
            List<Promotion> promotions = (List<Promotion>) request.getAttribute("promotions"); 
            if (promotions != null && !promotions.isEmpty()) { 
        %>
        <div class="mt-12">
            <h3 class="text-xl font-semibold text-gray-800 mb-4 flex items-center">
                <i data-lucide="list" class="w-5 h-5 mr-2 text-blue-500"></i>
                Promotions existantes
            </h3>
            
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Classe</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Reduction</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Sieges</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <% for (Promotion promo : promotions) { %>
                        <tr class="hover:bg-gray-50">
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                <%= promo.getNomClasse() != null ? promo.getNomClasse() : promo.getIdClasse() %>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-blue-600 font-medium">
                                <%= promo.getPourcentage() * 100 %>%
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <%= promo.getNbSiege() %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <% } else { %>
        <div class="mt-8 text-center">
            <div class="p-6 text-gray-400 flex flex-col items-center">
                <i data-lucide="tags" class="w-12 h-12 mb-3"></i>
                <p class="text-lg font-medium">Aucune promotion active</p>
                <p class="text-sm mt-1">Ajoutez une promotion ci-dessus</p>
            </div>
        </div>
        <% } %>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
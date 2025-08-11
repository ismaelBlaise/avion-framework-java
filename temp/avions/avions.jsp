<%@ page import="java.util.List" %>
<%@ page import="models.Avion" %>

<div class="py-6 max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
    <!-- Header avec titre et bouton -->
    <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
        <!-- <div>
            <h1 class="text-2xl font-bold text-gray-800">Gestion de la flotte aerienne</h1>
            <p class="text-sm text-gray-500 mt-1">Liste des appareils disponibles</p>
        </div> -->
        <a href="avions-ajout-form" class="inline-flex items-center px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-md shadow-sm transition-colors">
            <i data-lucide="plus" class="w-5 h-5 mr-2"></i>
            Ajouter un avion
        </a>
    </div>

    <!-- Tableau des avions -->
    <div class="bg-white shadow rounded-lg overflow-hidden">
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                    <tr>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="hash" class="w-4 h-4 mr-2"></i>
                                ID Avion
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="users" class="w-4 h-4 mr-2"></i>
                                Capacite
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="airplane" class="w-4 h-4 mr-2"></i>
                                Modele
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <%
                        List<Avion> avions = (List<Avion>) request.getAttribute("avions");
                        if (avions != null && !avions.isEmpty()) {
                            for (Avion avion : avions) {
                    %>
                    <tr class="hover:bg-gray-50 transition-colors">
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                            <%= avion.getIdAvion() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= avion.getCapacite() %> passagers
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <div class="flex items-center">
                                <i data-lucide="airplane" class="w-5 h-5 mr-2 text-blue-500"></i>
                                <span class="font-medium"><%= avion.getModele() %></span>
                            </div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                            <div class="flex justify-end space-x-3">
                                <a href="avions-update-form?id=<%= avion.getIdAvion() %>" class="inline-flex items-center px-3 py-1 border border-blue-500 rounded-md text-blue-600 hover:bg-blue-50 transition-colors">
                                    <i data-lucide="edit" class="w-4 h-4 mr-1"></i>
                                    Modifier
                                </a>
                                <a href="avions-delete?id=<%= avion.getIdAvion() %>" class="inline-flex items-center px-3 py-1 border border-red-500 rounded-md text-red-600 hover:bg-red-50 transition-colors">
                                    <i data-lucide="trash-2" class="w-4 h-4 mr-1"></i>
                                    Supprimer
                                </a>
                            </div>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" class="px-6 py-8 text-center">
                            <div class="flex flex-col items-center justify-center text-gray-400">
                                <i data-lucide="alert-circle" class="w-12 h-12 mb-3"></i>
                                <p class="text-lg font-medium">Aucun avion enregistre</p>
                                <p class="text-sm mt-1">Commencez par ajouter un nouvel avion</p>
                            </div>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    // Initialisation des icônes Lucide
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
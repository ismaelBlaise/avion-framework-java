<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="services.*" %>

<div class="py-6 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <!-- Header avec bouton -->
    <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
        <div>
            <h1 class="text-2xl font-bold text-gray-800">Gestion des vols</h1>
            <p class="text-sm text-gray-500 mt-1">Liste des vols programmes</p>
        </div>
        <a href="vols-ajout-form" class="inline-flex items-center px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white font-medium rounded-md shadow-sm">
            <i data-lucide="plus" class="w-5 h-5 mr-2"></i>
            Ajouter un vol
        </a>
    </div>

    <!-- Tableau des vols -->
    <div class="bg-white shadow rounded-lg overflow-hidden">
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                    <tr>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="airplane" class="w-4 h-4 mr-2"></i>
                                Avion
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="hash" class="w-4 h-4 mr-2"></i>
                                Numero
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="calendar" class="w-4 h-4 mr-2"></i>
                                Depart
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="calendar" class="w-4 h-4 mr-2"></i>
                                Arrivee
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="clock" class="w-4 h-4 mr-2"></i>
                                Fin reservation
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="clock" class="w-4 h-4 mr-2"></i>
                                Fin annulation
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="map-pin" class="w-4 h-4 mr-2"></i>
                                Ville depart
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="map-pin" class="w-4 h-4 mr-2"></i>
                                Ville arrivee
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Statut
                        </th>
                        <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <%
                        List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                        StatutService statutService=new StatutService();
                        if (vols != null && !vols.isEmpty()) {
                            for (Vol vol : vols) {
                    %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                            <%= vol.getModeleAvion() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getNumero() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getDepart() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getArrivee() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getFinReservation()==null? "Non configure":vol.getFinReservation() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getFinAnnulation()==null? "Non configure":vol.getFinAnnulation() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getVilleDepart() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= vol.getVilleArrive() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm">
                            <%
                                String statutVol = statutService.findStatutVols(vol).getStatut();
                                String bgColor = "";
                                String textColor = "";
                                
                                if ("Disponible".equals(statutVol)) {
                                    bgColor = "bg-green-100";
                                    textColor = "text-green-800";
                                } else if ("Non disponible".equals(statutVol)) {
                                    bgColor = "bg-red-100";
                                    textColor = "text-red-800";
                                } else {
                                    bgColor = "bg-yellow-100";
                                    textColor = "text-yellow-800";
                                }
                            %>
                            <span class="px-2 py-1 rounded-full text-xs <%= bgColor %> <%= textColor %>">
                                <%= statutVol %>
                            </span>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                            <div class="flex justify-end space-x-3">
                                <a href="vols-update-form?id=<%= vol.getIdVol() %>" class="text-blue-600 hover:text-blue-900">
                                    <i data-lucide="edit" class="w-4 h-4"></i>
                                </a>
                                <a href="vols-delete?id=<%= vol.getIdVol() %>" class="text-red-600 hover:text-red-900">
                                    <i data-lucide="trash-2" class="w-4 h-4"></i>
                                </a>
                                <a href="vols-configuration?id=<%= vol.getIdVol() %>" class="text-green-600 hover:text-green-900">
                                    <i data-lucide="settings" class="w-4 h-4"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="10" class="px-6 py-8 text-center">
                            <div class="flex flex-col items-center justify-center text-gray-400">
                                <i data-lucide="alert-circle" class="w-12 h-12 mb-3"></i>
                                <p class="text-lg font-medium">Aucun vol programme</p>
                                <p class="text-sm mt-1">Commencez par ajouter un nouveau vol</p>
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
<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<div class="max-w-7xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg overflow-hidden">
        <!-- Header -->
        <div class="px-6 py-5 border-b border-gray-200">
            <h2 class="text-xl font-semibold text-gray-800 flex items-center">
                <i data-lucide="ticket" class="w-6 h-6 text-blue-500 mr-2"></i>
                Gestion des reservations
            </h2>
        </div>

        <!-- Messages -->
        <% 
            String succes = (String) request.getAttribute("succes"); 
            String erreur = (String) request.getAttribute("erreur");
        %>
        <% if (succes != null) { %>
            <div class="p-4 text-green-700 bg-green-50 border-l-4 border-green-500 rounded-md mx-6 mt-4 flex items-start">
                <i data-lucide="check-circle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                <div><%= succes %></div>
            </div>
        <% } %>
        <% if (erreur != null) { %>
            <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md mx-6 mt-4 flex items-start">
                <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                <div><%= erreur %></div>
            </div>
        <% } %>

        <!-- Table -->
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                    <tr>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="hash" class="w-4 h-4 mr-2"></i>
                                ID Reservation
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="calendar" class="w-4 h-4 mr-2"></i>
                                Date
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="user" class="w-4 h-4 mr-2"></i>
                                Utilisateur
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="plane" class="w-4 h-4 mr-2"></i>
                                Vol
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="flag" class="w-4 h-4 mr-2"></i>
                                Statut
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <%
                        List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        
                        if (reservations != null && !reservations.isEmpty()) {
                            for (Reservation reservation : reservations) {
                    %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                            <%= reservation.getIdReservation() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= dateFormat.format(reservation.getDateReservation()) %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= reservation.getUtilisateurNom() != null ? reservation.getUtilisateurNom() : "N/A" %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= reservation.getVolNom() != null ? reservation.getVolNom() : "N/A" %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm">
                            <span class="px-2 py-1 rounded-full text-xs 
                                <%= "Confirme".equals(reservation.getStatutNom()) ? "bg-green-100 text-green-800" : 
                                   "Annulee".equals(reservation.getStatutNom()) ? "bg-red-100 text-red-800" : 
                                   "bg-yellow-100 text-yellow-800" %>">
                                <%= reservation.getStatutNom() != null ? reservation.getStatutNom() : "N/A" %>
                            </span>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                            <div class="flex justify-end space-x-3">
                                <a href="reservation-details?id=<%= reservation.getIdReservation() %>" 
                                   class="inline-flex items-center px-3 py-1 border border-blue-500 rounded-md text-blue-600 hover:bg-blue-50">
                                    <i data-lucide="eye" class="w-4 h-4 mr-1"></i>
                                    Details
                                </a>
                                <% if (!"Annulee".equals(reservation.getStatutNom())) { %>
                                    <a href="annuler-reservation?id=<%= reservation.getIdReservation() %>" 
                                       class="inline-flex items-center px-3 py-1 border border-red-500 rounded-md text-red-600 hover:bg-red-50">
                                        <i data-lucide="x" class="w-4 h-4 mr-1"></i>
                                        Annuler
                                    </a>
                                <% } %>
                            </div>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="6" class="px-6 py-8 text-center">
                            <div class="flex flex-col items-center justify-center text-gray-400">
                                <i data-lucide="alert-circle" class="w-12 h-12 mb-3"></i>
                                <p class="text-lg font-medium">Aucune reservation trouvee</p>
                                <p class="text-sm mt-1">Aucune reservation ne correspond a votre recherche</p>
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
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
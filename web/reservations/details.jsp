<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.util.Locale" %>

<div class="max-w-7xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg overflow-hidden">
        <!-- Header -->
        <div class="px-6 py-5 border-b border-gray-200 flex justify-between items-center">
            <h2 class="text-xl font-semibold text-gray-800 flex items-center">
                <i data-lucide="ticket" class="w-6 h-6 text-blue-500 mr-2"></i>
                Details de la reservation #<%= reservation.getIdReservation() %>
            </h2>
            
            <%
                String statut = (String) request.getAttribute("statut");
                boolean isAnnulee = "Annulee".equalsIgnoreCase(statut);
            %>
            
            <a href="detail-form?id=<%= reservation.getIdReservation() %>" 
               class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 <%= isAnnulee ? "opacity-50 cursor-not-allowed pointer-events-none" : "" %>"
               <%= isAnnulee ? "tabindex=\"-1\" aria-disabled=\"true\"" : "" %>>
                <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                Ajouter un detail
            </a>
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
                                ID
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="users" class="w-4 h-4 mr-2"></i>
                                Categorie d'âge
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center">
                                <i data-lucide="star" class="w-4 h-4 mr-2"></i>
                                Classe
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center justify-end">
                                <i data-lucide="dollar-sign" class="w-4 h-4 mr-2"></i>
                                Prix unitaire
                            </div>
                        </th>
                        <th scope="col" class="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                            <div class="flex items-center justify-center">
                                <i data-lucide="file-text" class="w-4 h-4 mr-2"></i>
                                Passeport
                            </div>
                        </th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <%
                        List<ReservationDetail> details = (List<ReservationDetail>) request.getAttribute("details");
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                        DecimalFormatSymbols symbols = ((DecimalFormat)currencyFormat).getDecimalFormatSymbols();
                        symbols.setCurrencySymbol("MGA");
                        ((DecimalFormat)currencyFormat).setDecimalFormatSymbols(symbols);

                        if (details != null && !details.isEmpty()) {
                            for (ReservationDetail detail : details) {
                    %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                            <%= detail.getIdReservationDetail() %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= detail.getCategorieAge() != null ? detail.getCategorieAge() : "N/A" %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                            <%= detail.getClasse() != null ? detail.getClasse() : "N/A" %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-right">
                            <%= currencyFormat.format(detail.getPrix()) %>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-center">
                            <% if ((detail.getPasseport() == null || detail.getNomFichier() == null)) { %>
                                <a href="import-form?id=<%= detail.getIdReservationDetail() %>" 
                                class="inline-flex items-center text-blue-600 hover:text-blue-800 <%= isAnnulee ? "opacity-50 cursor-not-allowed pointer-events-none" : "" %>" 
                                <%= isAnnulee ? "tabindex=\"-1\" aria-disabled=\"true\"" : "" %>>
                                    <i data-lucide="upload" class="w-4 h-4 mr-1"></i>
                                    Importer
                                </a>
                            <% } else { %>
                                <a href="voir-passport?id=<%= detail.getIdReservationDetail() %>" 
                                class="inline-flex items-center text-blue-600 hover:text-blue-800 <%= isAnnulee ? "opacity-50 cursor-not-allowed pointer-events-none" : "" %>" 
                                <%= isAnnulee ? "tabindex=\"-1\" aria-disabled=\"true\"" : "" %>>
                                    <i data-lucide="eye" class="w-4 h-4 mr-1"></i>
                                    Voir
                                </a>
                            <% } %>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="5" class="px-6 py-8 text-center">
                            <div class="flex flex-col items-center justify-center text-gray-400">
                                <i data-lucide="alert-circle" class="w-12 h-12 mb-3"></i>
                                <p class="text-lg font-medium">Aucun detail trouve</p>
                                <p class="text-sm mt-1">Cette reservation ne contient aucun detail</p>
                            </div>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
        
        <!-- Footer with back button -->
        <div class="px-6 py-4 border-t border-gray-200 flex justify-end">
            <a href="reservations" 
               class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                <i data-lucide="arrow-left" class="w-4 h-4 mr-2"></i>
                Retour a la liste des reservations
            </a>
        </div>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
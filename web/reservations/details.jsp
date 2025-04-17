<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.util.Locale" %>

<div class="py-6 max-w-6xl mx-auto">
    <%
        Reservation reservation = (Reservation) request.getAttribute("reservation");
        if (reservation == null) {
            response.sendRedirect("reservations");
            return;
        }
    %>
    
    <!-- Titre avec l'ID de reservation -->
    <div class="flex justify-between items-center mb-4">
        <h2 class="text-2xl font-bold">Details de la reservation #<%= reservation.getIdReservation() %></h2>
        <a href="detail-form?id=<%= reservation.getIdReservation() %>" 
           class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded-md transition-colors">
            Ajouter un detail
        </a>
    </div>

    <!-- Tableau des details -->
    <div class="overflow-x-auto bg-white rounded-lg shadow-md border border-gray-200">
        <table class="min-w-full">
            <thead>
                <tr class="bg-gray-100 text-gray-700">
                    <th class="px-4 py-3 text-left">Categorie d'âge</th>
                    <th class="px-4 py-3 text-left">Classe</th>
                    <th class="px-4 py-3 text-right">Prix unitaire</th>
                    <th class="px-4 py-3 text-center">Place</th>
                    <th class="px-4 py-3 text-center">Passeports</th>
                    <th class="px-4 py-3 text-right">Total</th>
                </tr>
            </thead>
            <tbody class="divide-y divide-gray-200">
                <%
                    List<ReservationDetail> details = (List<ReservationDetail>) request.getAttribute("details");
                    Locale malagasyLocale = new Locale("fr", "MG");
                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                    DecimalFormatSymbols symbols = ((DecimalFormat)currencyFormat).getDecimalFormatSymbols();
                    symbols.setCurrencySymbol("MGA");
                    ((DecimalFormat)currencyFormat).setDecimalFormatSymbols(symbols);
                    
                    if (details != null && !details.isEmpty()) {
                        double totalGeneral = 0;
                        
                        for (ReservationDetail detail : details) {
                            double totalLigne = detail.getPrix() * detail.getNb();
                            totalGeneral += totalLigne;
                %>
                <tr class="hover:bg-gray-50">
                    <td class="px-4 py-3"><%= detail.getCategorieAge() != null ? detail.getCategorieAge() : "N/A" %></td>
                    <td class="px-4 py-3"><%= detail.getClasse() != null ? detail.getClasse() : "N/A" %></td>
                    <td class="px-4 py-3 text-right"><%= currencyFormat.format(detail.getPrix()) %></td>
                    <td class="px-4 py-3 text-center"><%= detail.getNb() %></td>
                    <td class="px-4 py-3 border-b flex space-x-2">
                        <a href="passeports?id=<%= reservation.getIdReservation() %>" 
                           class="text-blue-500 hover:text-blue-700 hover:underline">passeports</a>
                    </td>
                    <td class="px-4 py-3 text-right font-medium"><%= currencyFormat.format(totalLigne) %></td>
                </tr>
                <% 
                        }
                %>
                <tr class="bg-gray-50 font-semibold">
                    <td colspan="3" class="px-4 py-3 text-right">Total general</td>
                    <td class="px-4 py-3 text-center"><%= details.stream().mapToInt(ReservationDetail::getNb).sum() %></td>
                    <td class="px-4 py-3 text-right"><%= currencyFormat.format(totalGeneral) %></td>
                </tr>
                <% 
                    } else {
                %>
                <tr>
                    <td colspan="5" class="px-6 py-4 text-center text-gray-500">Aucun detail trouve pour cette reservation</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
    
    <!-- Bouton de retour -->
    <div class="mt-6">
        <a href="reservations" 
           class="inline-flex items-center px-4 py-2 bg-gray-200 hover:bg-gray-300 text-gray-800 rounded-md transition-colors">
            Retour a la liste des reservations
        </a>
    </div>
</div>
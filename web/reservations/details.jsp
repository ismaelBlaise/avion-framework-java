<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>

<div class="py-6 max-w-6xl mx-auto">
    <!-- Titre avec l'ID de reservation -->
     <%
        Reservation reservation=(Reservation) request.getAttribute("reservation");
     %>
    <h2 class="text-2xl font-bold mb-4">Details de la reservation #<%= reservation.getIdReservation() %></h2>
    
    <!-- Tableau des details -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <th class="px-4 py-3 border-b">Categorie d'age</th>
                    <th class="px-4 py-3 border-b">Classe</th>
                    <th class="px-4 py-3 border-b">Prix unitaire</th>
                    <th class="px-4 py-3 border-b">Quantite</th>
                    <th class="px-4 py-3 border-b">Total</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <%
                    List<ReservationDetail> details = (List<ReservationDetail>) request.getAttribute("details");
                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                    
                    if (details != null && !details.isEmpty()) {
                        double totalGeneral = 0;
                        
                        for (ReservationDetail detail : details) {
                            double totalLigne = detail.getPrix() * detail.getNb();
                            totalGeneral += totalLigne;
                %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-4 py-3 border-b"><%= detail.getCategorieAge() != null ? detail.getCategorieAge() : "N/A" %></td>
                        <td class="px-4 py-3 border-b"><%= detail.getClasse() != null ? detail.getClasse() : "N/A" %></td>
                        <td class="px-4 py-3 border-b text-right"><%= currencyFormat.format(detail.getPrix()) %></td>
                        <td class="px-4 py-3 border-b text-center"><%= detail.getNb() %></td>
                        <td class="px-4 py-3 border-b text-right font-medium"><%= currencyFormat.format(totalLigne) %></td>
                    </tr>
                <% 
                        }
                %>
                    <!-- Ligne du total general -->
                    <tr class="bg-gray-50 font-semibold">
                        <td colspan="4" class="px-4 py-3 border-b text-right">Total general</td>
                        <td class="px-4 py-3 border-b text-right"><%= currencyFormat.format(totalGeneral) %></td>
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
        <a href="reservations" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors">
            Retour à la liste des reservations
        </a>
    </div>
</div>
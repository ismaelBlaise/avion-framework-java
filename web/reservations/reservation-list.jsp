<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="services.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<div class="py-6 max-w-6xl mx-auto">
    <!-- Tableau des réservations -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <th class="px-4 py-3 border-b">ID Réservation</th>
                    <th class="px-4 py-3 border-b">Date Réservation</th>
                    <th class="px-4 py-3 border-b">Utilisateur</th>
                    <th class="px-4 py-3 border-b">Vol</th>
                    <th class="px-4 py-3 border-b">Classe</th>
                    <th class="px-4 py-3 border-b">Statut</th>
                    <th class="px-4 py-3 border-b">Actions</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <%
                    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                    StatutService statutService = new StatutService();
                    UtilisateurService utilisateurService = new UtilisateurService();
                    VolService volService = new VolService();
                    ClasseService classeService = new ClasseService();
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    
                    if (reservations != null && !reservations.isEmpty()) {
                        for (Reservation reservation : reservations) {
                            Statut statut = statutService.findById(reservation.getIdStatut());
                            Utilisateur utilisateur = utilisateurService.findById(reservation.getIdUtilisateur());
                            Vol vol = volService.getVolById(Long.valueOf(reservation.getIdVol()));
                            Classe classe = classeService.findById(reservation.getIdClasse());
                %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-4 py-3 border-b"><%= reservation.getIdReservation() %></td>
                        <td class="px-4 py-3 border-b"><%= dateFormat.format(reservation.getDateReservation()) %></td>
                        <td class="px-4 py-3 border-b"><%= utilisateur != null ? utilisateur.getNom() + " " + utilisateur.getPrenom() : "N/A" %></td>
                        <td class="px-4 py-3 border-b">
                            <%= vol != null ? vol.getNumero() + " (" + vol.getDateVol() + ")" : "N/A" %>
                        </td>
                        <td class="px-4 py-3 border-b"><%= classe != null ? classe.getNom() : "N/A" %></td>
                        <td class="px-4 py-3 border-b">
                            <span class="px-2 py-1 rounded-full text-xs 
                                <%= "Confirme".equals(statut.getStatut()) ? "bg-green-100 text-green-800" : 
                                   "Annulee".equals(statut.getStatut()) ? "bg-red-100 text-red-800" : 
                                   "bg-yellow-100 text-yellow-800" %>">
                                <%= statut.getStatut() %>
                            </span>
                        </td>
                        <td class="px-4 py-3 border-b flex space-x-2">
                            <a href="reservation-details?id=<%= reservation.getIdReservation() %>" 
                               class="text-blue-500 hover:text-blue-700 hover:underline">Détails</a>
                            <% if (!"Annulee".equals(statut.getStatut())) { %>
                                <a href="annuler-reservation?id=<%= reservation.getIdReservation() %>" 
                                   class="text-red-500 hover:text-red-700 hover:underline">Annuler</a>
                            <% } %>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" class="px-6 py-4 text-center text-gray-500">Aucune réservation trouvée</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</div>
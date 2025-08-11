<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="services.*" %>


<div class="py-6 max-w-4xl mx-auto">
    <!-- Bouton Ajouter un vol -->
    

    <!-- Tableau des vols -->
    <div class="overflow-x-auto">
    <h2 class="text-2xl font-semibold text-gray-700 mb-6">Les vols displonible</h2>
        
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <th class="px-4 py-3 border-b">Model d'avion</th>
                    <th class="px-4 py-3 border-b">Numero</th>
                    
                    <th class="px-4 py-3 border-b">Date de Depart</th>
                    <th class="px-4 py-3 border-b">Date d'Arrivee</th>
                    <th class="px-4 py-3 border-b">Fin de reservation</th>
                    <th class="px-4 py-3 border-b">Fin d'annulation</th>
                    <th class="px-4 py-3 border-b">Ville Depart</th>
                    <th class="px-4 py-3 border-b">Ville Arrivee</th>
                    <!-- <th class="px-4 py-3 border-b">Statut</th> -->
                    <th class="px-4 py-3 border-b">Actions</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <%
                    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                    StatutService statutService=new StatutService();
                    if (vols != null && !vols.isEmpty() ) {
                        for (Vol vol : vols) {
                            
                                
                %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-4 py-3 border-b"><%= vol.getModeleAvion() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getNumero() %></td>
                        
                        <td class="px-4 py-3 border-b"><%= vol.getDepart() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getArrivee() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getFinReservation()==null? "Non configurer":vol.getFinReservation() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getFinAnnulation()==null? "Non configurer":vol.getFinAnnulation() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getVilleDepart() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getVilleArrive() %></td>
                        <!-- <td class="px-4 py-3 border-b"><%= statutService.findStatutVols(vol).getStatut() %></td> -->
                        <td class="px-4 py-3 border-b flex space-x-2">
                            <a href="vols-reserver-form?id=<%= vol.getIdVol() %>"><button class="text-blue-500 hover:text-blue-700">Resrever</button></a>
                            
                        </td>
                    </tr>
                <% 
                
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="6" class="px-6 py-4 text-center text-gray-500">Aucun vol trouve</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="services.*" %>


<div class="py-6 max-w-4xl mx-auto">
    <!-- Bouton Ajouter un vol -->
    <div class="mb-4">
        <a href="vols-ajout-form">
            <button class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter un vol
            </button>
        </a>
    </div>

    <!-- Tableau des vols -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <!-- <th class="px-4 py-3 border-b">ID Vol</th> -->
                    <th class="px-4 py-3 border-b">Numero</th>
                    <!-- <th class="px-4 py-3 border-b">Date de Vol</th> -->
                    <th class="px-4 py-3 border-b">Date de Depart</th>
                    <th class="px-4 py-3 border-b">Date d'Arrivee</th>
                    <th class="px-4 py-3 border-b">Date limite reservation</th>
                    <th class="px-4 py-3 border-b">Date limite annulation</th>
                    <th class="px-4 py-3 border-b">Statut</th>
                    <th class="px-4 py-3 border-b">Actions</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <!-- Iteration sur la liste des vols -->
                <%
                    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                    StatutService statutService=new StatutService();
                    if (vols != null && !vols.isEmpty()) {
                        for (Vol vol : vols) {
                            
                                
                %>
                    <tr class="hover:bg-gray-50">
                        <!-- <td class="px-4 py-3 border-b"><%= vol.getIdVol() %></td> -->
                        <td class="px-4 py-3 border-b"><%= vol.getNumero() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getDepart() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getArrivee() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getFinReservation()==null? "Non configurer":vol.getFinReservation() %></td>
                        <td class="px-4 py-3 border-b"><%= vol.getFinAnnulation()==null? "Non configurer":vol.getFinAnnulation() %></td>
                        <td class="px-4 py-3 border-b">
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
                        <td class="px-4 py-3 border-b flex space-x-2">
                            <a href="vols-update-form?id=<%= vol.getIdVol() %>"><button class="text-blue-500 hover:text-blue-700">Modifier</button></a>
                            <a href="vols-delete?id=<%= vol.getIdVol() %>"><button class="text-red-500 hover:text-red-700">Supprimer</button></a>
                            <a href="vols-configuration?id=<%= vol.getIdVol() %>">
                                <button class="text-green-500 hover:text-green-700">Configuration</button>
                            </a>
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

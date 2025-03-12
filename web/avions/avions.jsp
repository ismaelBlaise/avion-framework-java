<%@ page import="java.util.List" %>
<%@ page import="models.Avion" %>

<div class="py-6 max-w-4xl mx-auto">
    <!-- Bouton Ajouter un avion -->
    <div class="mb-4">
        <a href="ajout.jsp">
            <button class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
                Ajouter un avion
            </button>
        </a>
    </div>

    <!-- Tableau des avions -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <th class="px-4 py-3 border-b">ID Avion</th>
                    <th class="px-4 py-3 border-b">Capacite</th>
                    <th class="px-4 py-3 border-b">Modele</th>
                    <th class="px-4 py-3 border-b">Actions</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <!-- Iteration sur la liste d'avions -->
                <%
                    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
                    if (avions != null) {
                        for (Avion avion : avions) {
                %>
                    <tr class="hover:bg-gray-50">
                        <td class="px-4 py-3 border-b"><%= avion.getIdAvion() %></td>
                        <td class="px-4 py-3 border-b"><%= avion.getCapacite() %></td>
                        <td class="px-4 py-3 border-b"><%= avion.getModele() %></td>
                        <td class="px-4 py-3 border-b flex space-x-2">
                            <a href="avions/update?id=<%= avion.getIdAvion() %>"><button class="text-blue-500 hover:text-blue-700">Modifier</button></a>
                            <a href="avions/delete?id=<%= avion.getIdAvion() %>"><button class="text-red-500 hover:text-red-700">Supprimer</button></a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="px-6 py-4 text-center text-gray-500">Aucun avion trouve</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

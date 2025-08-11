<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Header with icon -->
        <div class="flex items-center mb-6">
            <i data-lucide="file-text" class="w-8 h-8 text-blue-500 mr-3"></i>
            <div>
                <h2 class="text-2xl font-semibold text-gray-800">Details de reservation</h2>
                <p class="text-sm text-gray-500">Pour la reservation #<span class="font-medium"><%= request.getAttribute("reservation") %></span></p>
            </div>
        </div>

        <!-- Form -->
        <form action="vols-reservation-details" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur");
                String succes = (String) request.getAttribute("succes");
                int reservation = (int) request.getAttribute("reservation");
                List<CategorieAge> categoriesAge = (List<CategorieAge>) request.getAttribute("categoriesAge");
                List<Classe> classes = (List<Classe>) request.getAttribute("classes");
            %>
            
            <!-- Messages -->
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <% if (succes != null) { %>
                <div class="p-4 text-green-700 bg-green-50 border-l-4 border-green-500 rounded-md flex items-start">
                    <i data-lucide="check-circle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= succes %></div>
                </div>
            <% } %>

            <input type="hidden" name="idReservation" value="<%= reservation %>">

            <!-- Class selection -->
            <div class="space-y-2">
                <label for="idClasse" class="block text-sm font-medium text-gray-700">Classe</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="layers" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idClasse" name="idClasse" required 
                            class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (Classe classe : classes) { %>
                            <option value="<%= classe.getIdClasse() %>"><%= classe.getClasse() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Promotion checkbox -->
            <div class="flex items-center space-x-3 pt-2">
                <div class="relative flex items-start">
                    <div class="flex items-center h-5">
                        <input id="promotion" name="promotion" type="checkbox" 
                               class="focus:ring-blue-500 h-4 w-4 text-blue-600 border-gray-300 rounded">
                    </div>
                    <div class="ml-3 text-sm">
                        <label for="promotion" class="font-medium text-gray-700 flex items-center">
                            <i data-lucide="tag" class="w-4 h-4 mr-1 text-blue-500"></i>
                            Appliquer une promotion
                        </label>
                    </div>
                </div>
            </div>

            <!-- Age category -->
            <div class="space-y-2">
                <label for="idCategorieAge" class="block text-sm font-medium text-gray-700">Categorie d'age</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="users" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <select id="idCategorieAge" name="idCategorieAge" required
                            class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                        <% for (CategorieAge categorie : categoriesAge) { %>
                            <option value="<%= categorie.getIdCategorieAge() %>"><%= categorie.getCategorie() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Number of seats -->
            <div class="space-y-2">
                <label for="nb" class="block text-sm font-medium text-gray-700">Nombre de places</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="user-plus" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="number" id="nb" name="nb" min="1" required
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Submit button -->
            <div class="flex justify-end pt-4">
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                    Ajouter les details
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
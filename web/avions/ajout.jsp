<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Titre avec icône -->
        <div class="flex items-center mb-6">
            <i data-lucide="airplane" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Ajouter un nouvel avion</h2>
        </div>

        <!-- Formulaire d'ajout d'avion -->
        <form action="avions-ajouter" method="POST" class="space-y-6">
            <% String erreur = (String) request.getAttribute("erreur"); %>
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>
            
            <!-- Champ Modèle -->
            <div class="space-y-2">
                <label for="modele" class="block text-sm font-medium text-gray-700">Modèle d'avion</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="type" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" id="modele" name="avion.modele" required 
                           placeholder="Ex: Boeing 747, Airbus A320..."
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Champ Capacité -->
            <div class="space-y-2">
                <label for="capacite" class="block text-sm font-medium text-gray-700">Capacité (passagers)</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="users" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="number" id="capacite" name="avion.capacite" required 
                           placeholder="Ex: 150, 250, 400..."
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Boutons -->
            <div class="flex justify-end space-x-4 pt-4">
                <a href="avions" class="inline-flex items-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50">
                    <i data-lucide="arrow-left" class="w-4 h-4 mr-2"></i>
                    Annuler
                </a>
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="plus" class="w-4 h-4 mr-2"></i>
                    Ajouter l'avion
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    // Initialisation des icônes Lucide
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
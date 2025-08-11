<div class="flex flex-col items-center bg-gray-50 min-h-screen py-8 px-4 sm:px-6 lg:px-8">
    <!-- Conteneur du menu -->
    <div class="bg-white shadow-md rounded-xl p-6 w-full max-w-md">
        <!-- En-tête avec icône -->
        <div class="flex flex-col items-center mb-6">
            <div class="bg-blue-100 p-3 rounded-full mb-3">
                <i data-lucide="settings" class="w-8 h-8 text-blue-600"></i>
            </div>
            <h2 class="text-xl font-semibold text-gray-800">Configuration du vol</h2>
            <p class="text-sm text-gray-500 mt-1">Gestion des parametres du vol</p>
        </div>

        <% String id=(String) request.getAttribute("id"); %>
        
        <!-- Boutons du menu -->
        <div class="space-y-4">
            <a href="vols-heure-reservation-form?id=<%=id%>" class="block">
                <button class="w-full flex items-center justify-between px-5 py-3 border border-gray-200 rounded-lg hover:bg-blue-50 hover:border-blue-200 transition-colors">
                    <div class="flex items-center">
                        <i data-lucide="clock" class="w-5 h-5 text-blue-500 mr-3"></i>
                        <span>Fin reservation</span>
                    </div>
                    <i data-lucide="chevron-right" class="w-5 h-5 text-gray-400"></i>
                </button>
            </a>
            
            <a href="vols-heure-annulation-form?id=<%=id%>" class="block">
                <button class="w-full flex items-center justify-between px-5 py-3 border border-gray-200 rounded-lg hover:bg-blue-50 hover:border-blue-200 transition-colors">
                    <div class="flex items-center">
                        <i data-lucide="calendar-x" class="w-5 h-5 text-blue-500 mr-3"></i>
                        <span>Fin d'annulation</span>
                    </div>
                    <i data-lucide="chevron-right" class="w-5 h-5 text-gray-400"></i>
                </button>
            </a>
            
            <a href="vols-caracteristique-form?id=<%=id%>" class="block">
                <button class="w-full flex items-center justify-between px-5 py-3 border border-gray-200 rounded-lg hover:bg-blue-50 hover:border-blue-200 transition-colors">
                    <div class="flex items-center">
                        <i data-lucide="list" class="w-5 h-5 text-blue-500 mr-3"></i>
                        <span>Caracteristiques du vol</span>
                    </div>
                    <i data-lucide="chevron-right" class="w-5 h-5 text-gray-400"></i>
                </button>
            </a>
            
            <a href="vols-promotion-form?id=<%=id%>" class="block">
                <button class="w-full flex items-center justify-between px-5 py-3 border border-gray-200 rounded-lg hover:bg-blue-50 hover:border-blue-200 transition-colors">
                    <div class="flex items-center">
                        <i data-lucide="tag" class="w-5 h-5 text-blue-500 mr-3"></i>
                        <span>Promotions</span>
                    </div>
                    <i data-lucide="chevron-right" class="w-5 h-5 text-gray-400"></i>
                </button>
            </a>
        </div>
    </div>
</div>

<script>
    // Initialisation des icônes Lucide
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
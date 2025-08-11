<%@ page import="java.util.List" %>
<%@ page import="models.*" %>

<div class="max-w-md mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg overflow-hidden border border-gray-200">
        <!-- Header with icon -->
        <div class="px-6 py-5 bg-gray-50 border-b border-gray-200">
            <div class="flex flex-col items-center">
                <div class="bg-blue-100 p-3 rounded-full mb-3">
                    <i data-lucide="upload" class="w-8 h-8 text-blue-600"></i>
                </div>
                <h2 class="text-2xl font-bold text-gray-800">Importer un passeport</h2>
                <p class="text-sm text-gray-500 mt-1">Detail de reservation #<%= ((ReservationDetail)request.getAttribute("detail")).getIdReservationDetail() %></p>
            </div>
        </div>

        <div class="p-6">
            <% 
                String erreur = (String) request.getAttribute("erreur"); 
                String succes = (String) request.getAttribute("succes");
                ReservationDetail detail = (ReservationDetail) request.getAttribute("detail");
            %>
            
            <!-- Messages -->
            <% if (erreur != null) { %>
                <div class="mb-4 p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <% if (succes != null) { %>
                <div class="mb-4 p-4 text-green-700 bg-green-50 border-l-4 border-green-500 rounded-md flex items-start">
                    <i data-lucide="check-circle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= succes %></div>
                </div>
            <% } %>

            <!-- Form -->
            <form method="post" action="import" enctype="multipart/form-data" class="space-y-6">
                <input type="hidden" name="id" value="<%= detail.getIdReservationDetail() %>" />

                <!-- File input -->
                <div class="space-y-2">
                    <label for="passeportFile" class="block text-sm font-medium text-gray-700">
                        <div class="flex items-center">
                            <i data-lucide="file" class="w-5 h-5 mr-2 text-gray-500"></i>
                            Fichier passeport (PDF, JPG, PNG)
                        </div>
                    </label>
                    <div class="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                        <div class="space-y-1 text-center">
                            <div class="flex text-sm text-gray-600">
                                <label for="passeportFile" class="relative cursor-pointer bg-white rounded-md font-medium text-blue-600 hover:text-blue-500 focus-within:outline-none">
                                    <span>Televerser un fichier</span>
                                    <input id="passeportFile" name="passport" type="file" 
                                           accept=".pdf,.jpg,.jpeg,.png" required
                                           class="sr-only">
                                </label>
                                <p class="pl-1">ou glisser-deposer</p>
                            </div>
                            <p class="text-xs text-gray-500">
                                PDF, JPG, PNG jusqu'à 10MB
                            </p>
                        </div>
                    </div>
                </div>

                <!-- Submit button -->
                <div class="flex justify-center">
                    <button type="submit" 
                            class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                        <i data-lucide="upload" class="w-4 h-4 mr-2"></i>
                        Importer le passeport
                    </button>
                </div>
            </form>
        </div>

        <!-- Footer with back link -->
        <div class="px-6 py-4 bg-gray-50 border-t border-gray-200 text-center">
            <a href="reservation-details?id=<%= detail.getIdReservation() %>" 
               class="inline-flex items-center text-sm font-medium text-blue-600 hover:text-blue-500">
               <i data-lucide="arrow-left" class="w-4 h-4 mr-1"></i>
               Retour aux details de la reservation
            </a>
        </div>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
    lucide.createIcons();

    const fileInput = document.getElementById('passeportFile');
    if (fileInput) {
        fileInput.addEventListener('change', function(e) {
            const fileName = e.target.files[0]?.name || 'Aucun fichier sélectionné';
            // On va chercher le span dans le label parent
            const labelSpan = this.parentElement.querySelector('span');
            if (labelSpan) {
                labelSpan.textContent = fileName;
            }
        });
    }
});

</script>
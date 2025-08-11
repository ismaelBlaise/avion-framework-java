<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <% 
            Vol vol = (Vol) request.getAttribute("vol");
            if (vol == null) {
                response.sendRedirect("vols-disponible");
                return;
            }
        %>
        <!-- Header with icon -->
        <div class="flex items-center mb-6">
            <i data-lucide="ticket" class="w-8 h-8 text-blue-500 mr-3"></i>
            <div>
                <h2 class="text-2xl font-semibold text-gray-800">Creer une reservation</h2>
                <p class="text-sm text-gray-500">Pour le vol <span class="font-medium"><%= vol.getNumero() %></span></p>
            </div>
        </div>

        <!-- Reservation form -->
        <form action="vols-reserver" method="POST" class="space-y-6">
            <% 
                String erreur = (String) request.getAttribute("erreur");
                List<Statut> statuts = (List<Statut>) request.getAttribute("statuts");
                List<Classe> classes = (List<Classe>) request.getAttribute("classes");
                Vol vol = (Vol) request.getAttribute("vol");
            %>
            
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>

            <!-- Flight info (readonly) -->
            <div class="space-y-2">
                <label class="block text-sm font-medium text-gray-700">Vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="plane" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%= vol.getNumero() %>" disabled
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-50">
                    <input type="hidden" name="reservation.idVol" value="<%= vol.getIdVol() %>">
                </div>
            </div>

            <!-- Reservation date -->
            <div class="space-y-2">
                <label for="dateReservation" class="block text-sm font-medium text-gray-700">Date de reservation</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="calendar" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="datetime-local" id="dateReservation" name="reservation.dateReservation" required
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
            </div>

            <!-- Additional fields (if needed) -->
            <!-- You can add more fields here following the same pattern -->

            <!-- Submit button -->
            <div class="flex justify-end pt-4">
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="save" class="w-4 h-4 mr-2"></i>
                    Confirmer la reservation
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
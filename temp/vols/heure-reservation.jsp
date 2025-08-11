<%@ page import="models.Vol" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow rounded-lg p-6 sm:p-8">
        <!-- Header with icon -->
        <div class="flex items-center mb-6">
            <i data-lucide="calendar-clock" class="w-8 h-8 text-blue-500 mr-3"></i>
            <h2 class="text-2xl font-semibold text-gray-800">Definir la limite de reservation</h2>
        </div>

        <!-- Form -->
        <form action="vols-heure-reservation" method="POST" class="space-y-6">
            <% String erreur = (String) request.getAttribute("erreur"); %>
            <% if (erreur != null) { %>
                <div class="p-4 text-red-700 bg-red-50 border-l-4 border-red-500 rounded-md flex items-start">
                    <i data-lucide="alert-triangle" class="w-5 h-5 mr-3 mt-0.5 flex-shrink-0"></i>
                    <div><%= erreur %></div>
                </div>
            <% } %>
            
            <% 
                Vol vol = (Vol) request.getAttribute("vol");
                LocalDateTime maintenant = LocalDateTime.now();
                DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateHeureFormatee = maintenant.format(formatteur);
            %>
            <input type="hidden" value="<%= vol.getIdVol() %>" id="id" name="id">

            <!-- Flight info (readonly) -->
            <div class="space-y-2">
                <label class="block text-sm font-medium text-gray-700">Vol</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="plane" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="text" value="<%= vol.getNumero() %>" disabled
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md bg-gray-50">
                </div>
            </div>

            <!-- Reservation limit field -->
            <div class="space-y-2">
                <label for="heureReservation" class="block text-sm font-medium text-gray-700">Date limite de reservation</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                        <i data-lucide="clock" class="w-5 h-5 text-gray-400"></i>
                    </div>
                    <input type="datetime-local" id="heureReservation" name="heureReservation" 
                           value="<%=dateHeureFormatee%>" required
                           class="pl-10 w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                </div>
                <p class="mt-1 text-sm text-gray-500">Definissez la date et heure limite pour reserver ce vol</p>
            </div>

            <!-- Submit button -->
            <div class="flex justify-end pt-4">
                <button type="submit" class="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <i data-lucide="save" class="w-4 h-4 mr-2"></i>
                    Enregistrer la limite
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
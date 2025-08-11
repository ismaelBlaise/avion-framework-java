<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>

<%
    String fileName = (String) request.getAttribute("filePath");
    String extension = null;
    if (fileName != null && fileName.contains(".")) {
        extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
    String id = (String) request.getAttribute("reservation");
    String fileUrl = null;
    if (fileName != null) {
        fileUrl = request.getContextPath() + "/assets/" + fileName;
    }
%>

<div class="max-w-4xl mx-auto py-8 px-4 sm:px-6 lg:px-8">
    <div class="bg-white shadow-xl rounded-lg overflow-hidden border border-gray-200">
        <!-- Header with icon -->
        <div class="px-6 py-5 bg-gray-50 border-b border-gray-200">
            <div class="flex flex-col items-center">
                <div class="bg-blue-100 p-3 rounded-full mb-3">
                    <i data-lucide="file-text" class="w-8 h-8 text-blue-600"></i>
                </div>
                <h2 class="text-2xl font-bold text-gray-800">Visualisation du passeport</h2>
                <p class="text-sm text-gray-500 mt-1">Reservation #<%= id %></p>
            </div>
        </div>

        <!-- Document display area -->
        <div class="p-6">
            <div class="flex justify-center items-center border-2 border-dashed border-gray-200 rounded-xl bg-gray-50 p-6 min-h-[500px]">
                <% if (fileUrl == null) { %>
                    <div class="text-center">
                        <i data-lucide="file-x" class="w-12 h-12 mx-auto text-red-400 mb-3"></i>
                        <p class="text-red-600 text-lg font-medium">Aucun fichier disponible</p>
                        <p class="text-gray-500 mt-1">Aucun passeport n'a ete telecharge pour cette reservation</p>
                    </div>
                <% } else if ("pdf".equals(extension)) { %>
                    <iframe src="<%= fileUrl %>" 
                            class="w-full h-[500px] rounded-lg shadow-sm border border-gray-200"
                            frameborder="0">
                    </iframe>
                <% } else if (Arrays.asList("jpg", "jpeg", "png", "gif").contains(extension)) { %>
                    <div class="max-w-full max-h-[500px] overflow-auto">
                        <img src="<%= fileUrl %>" 
                             alt="Passeport" 
                             class="rounded-lg shadow-sm object-contain max-h-[480px] mx-auto border border-gray-200" />
                    </div>
                <% } else { %>
                    <div class="text-center">
                        <i data-lucide="alert-circle" class="w-12 h-12 mx-auto text-yellow-400 mb-3"></i>
                        <p class="text-yellow-600 text-lg font-medium">Format non supporte</p>
                        <p class="text-gray-500 mt-1">Le format .<%= extension %> ne peut pas etre affiche directement</p>
                        <div class="mt-4">
                            <a href="<%= fileUrl %>" download
                               class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700">
                                <i data-lucide="download" class="w-4 h-4 mr-2"></i>
                                Telecharger le fichier
                            </a>
                        </div>
                    </div>
                <% } %>
            </div>
        </div>

        <!-- Footer with back button -->
        <div class="px-6 py-4 bg-gray-50 border-t border-gray-200 text-center">
            <a href="reservation-details?id=<%= id %>" 
               class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700">
               <i data-lucide="arrow-left" class="w-4 h-4 mr-2"></i>
               Retour aux details de la reservation
            </a>
        </div>
    </div>
</div>

<script>
    // Initialize Lucide icons
    document.addEventListener('DOMContentLoaded', function() {
        lucide.createIcons();
    });
</script>
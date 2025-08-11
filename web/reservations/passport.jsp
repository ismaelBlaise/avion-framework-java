<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>

<%
    // filePath contient juste le nom du fichier, par ex: "monfichier.pdf"
    String fileName = (String) request.getAttribute("filePath");
    String extension = null;

    if (fileName != null && fileName.contains(".")) {
        extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
    String id=(String)request.getAttribute("reservation");
    // Construction du chemin relatif web (URL)
    String fileUrl = null;
    if (fileName != null) {
        fileUrl = request.getContextPath() + "/assets/" + fileName;
    }
%>

<div class="py-8 max-w-4xl mx-auto bg-white rounded-xl shadow-lg border border-gray-300 p-8">
    <h2 class="text-3xl font-extrabold mb-6 text-center text-gray-900">Visualisation du passeport</h2>

    <div class="flex justify-center items-center border border-gray-200 rounded-md bg-gray-50 p-4 min-h-[650px]">
    <%
        if (fileUrl == null) {
    %>
        <p class="text-red-600 text-lg font-semibold">Aucun fichier disponible.</p>
    <%
        } else if ("pdf".equals(extension)) {
    %>
        <iframe src="<%=fileUrl%>" 
            width="100%" 
            height="600px" 
            style="border:none;">
        </iframe>

    <%
        } else if ("jpg".equals(extension) || "jpeg".equals(extension) || "png".equals(extension) || "gif".equals(extension)) {
    %>
        <img src="<%= fileUrl %>" 
             alt="Passeport" 
             class="max-w-full max-h-[600px] rounded-md shadow-md object-contain" />
    <%
        } else {
    %>
        <p class="text-yellow-600 text-lg font-semibold">Format de fichier non supporte pour l'affichage : <%= extension %></p>
    <%
        }
    %>
    </div>

    <div class="mt-8 text-center">
        <a href="reservation-details?id=<%= %>" 
           class="inline-block px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition-colors">
           ← Retour aux details de la reservation
        </a>
    </div>
</div>

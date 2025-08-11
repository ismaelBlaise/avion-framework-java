<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - AeroPlan Gestion de Vols</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        .bg-aviation {
            background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);
        }
        .logo-text {
            font-family: 'Arial', sans-serif;
            font-weight: bold;
            background: linear-gradient(90deg, #3b82f6 0%, #1e40af 100%);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
        }
    </style>
</head>
<body class="bg-aviation flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-xl shadow-2xl w-96 border border-gray-100">
        <!-- Logo AeroPlan -->
        <div class="flex flex-col items-center mb-6">
            <div class="flex items-center justify-center mb-2">
                <i data-lucide="plane" class="w-10 h-10 text-blue-600"></i>
                <span class="logo-text text-3xl ml-2">AeroPlan</span>
            </div>
            <p class="text-sm text-gray-500">Gestion des vols aeroportuaires</p>
        </div>
        
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Se connecter</h2>

        <!-- Affichage du message d'erreur -->
        <% String erreur = (String) request.getAttribute("erreur"); %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm flex items-start">
                <i data-lucide="alert-circle" class="w-4 h-4 mt-0.5 mr-2 flex-shrink-0"></i>
                <span><%= erreur %></span>
            </div>
        <% } %>

        <form action="login" method="post"> 
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-medium mb-1">Identifiant</label>
                <div class="flex items-center border border-gray-300 rounded-lg px-3 py-2 mt-1 focus-within:border-blue-500 focus-within:ring-1 focus-within:ring-blue-500">
                    <i data-lucide="user" class="w-5 h-5 text-gray-500"></i>
                    <input type="email" name="user.email" placeholder="email@aeroplan.com"
                        class="w-full outline-none pl-2 text-gray-700 placeholder-gray-400 text-sm">
                </div>
            </div>

            <div class="mb-6">
                <label class="block text-gray-700 text-sm font-medium mb-1">Mot de passe</label>
                <div class="flex items-center border border-gray-300 rounded-lg px-3 py-2 mt-1 focus-within:border-blue-500 focus-within:ring-1 focus-within:ring-blue-500">
                    <i data-lucide="lock" class="w-5 h-5 text-gray-500"></i>
                    <input type="password" name="user.mdp" placeholder="Entrez votre mot de passe"
                        class="w-full outline-none pl-2 text-gray-700 placeholder-gray-400 text-sm">
                </div>
            </div>

            <button type="submit"
                class="w-full bg-blue-600 text-white py-2.5 rounded-lg font-semibold hover:bg-blue-700 transition flex items-center justify-center">
                <i data-lucide="log-in" class="w-4 h-4 mr-2"></i>
                Connexion
            </button>
        </form>

        
    </div>

    <script>
        lucide.createIcons();
    </script>
</body>
</html>
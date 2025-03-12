<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Aeroport</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-96">
        <!-- Logo -->
        <div class="flex justify-center mb-4">
            <i data-lucide="airplane" class="w-12 h-12 text-blue-500"></i>
        </div>
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Connexion</h2>

        <!-- Affichage du message d'erreur -->
        <% String erreur = (String) request.getAttribute("erreur"); %>
        <% if (erreur != null) { %>
            <div class="mb-4 p-3 text-red-700 bg-red-100 border border-red-400 rounded-lg text-sm">
                <%= erreur %>
            </div>
        <% } %>
        <form action="login" method="post"> 
            <div class="mb-4">
                <label class="block text-gray-700">Email</label>
                <div class="flex items-center border rounded-lg px-3 py-2 mt-1">
                    <i data-lucide="mail" class="w-5 h-5 text-gray-500"></i>
                    <input type="email" placeholder="exemple@email.com"
                        class="w-full outline-none pl-2 text-gray-700">
                </div>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700">Mot de passe</label>
                <div class="flex items-center border rounded-lg px-3 py-2 mt-1">
                    <i data-lucide="lock" class="w-5 h-5 text-gray-500"></i>
                    <input type="password" 
                        class="w-full outline-none pl-2 text-gray-700">
                </div>
            </div>

            <button type="submit"
                class="w-full bg-blue-500 text-white py-2 rounded-lg font-bold hover:bg-blue-600 transition">
                Se connecter
            </button>
        </form>

        <!-- Liens -->
        <!-- <div class="text-center mt-4">
            <p class="text-sm mt-2">
                Pas encore inscrit ?
                <a href="roles" class="text-blue-500 hover:underline">Creer un compte</a>
            </p>
        </div> -->
    </div>

    <script>
        lucide.createIcons();
    </script>
</body>
</html>

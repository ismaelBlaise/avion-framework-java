<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription - Aéroport</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-96">
        <!-- Logo -->
        <div class="flex justify-center mb-4">
            <i data-lucide="airplane" class="w-12 h-12 text-blue-500"></i>
        </div>
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-4">Créer un compte</h2>

        <form>
            <div class="mb-3">
                <label class="block text-gray-700">Nom</label>
                <input type="text" placeholder="Votre nom"
                    class="w-full border rounded-lg px-3 py-2 mt-1 outline-none text-gray-700">
            </div>

            <div class="mb-3">
                <label class="block text-gray-700">Prénom</label>
                <input type="text" placeholder="Votre prénom"
                    class="w-full border rounded-lg px-3 py-2 mt-1 outline-none text-gray-700">
            </div>

            <div class="mb-3">
                <label class="block text-gray-700">Contact</label>
                <input type="text" placeholder="Numéro de téléphone"
                    class="w-full border rounded-lg px-3 py-2 mt-1 outline-none text-gray-700">
            </div>

            <div class="mb-3">
                <label class="block text-gray-700">Date de naissance</label>
                <input type="date"
                    class="w-full border rounded-lg px-3 py-2 mt-1 outline-none text-gray-700">
            </div>

            <div class="mb-3">
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
                    <input type="password" placeholder="••••••••"
                        class="w-full outline-none pl-2 text-gray-700">
                </div>
            </div>

            <button type="submit"
                class="w-full bg-blue-500 text-white py-2 rounded-lg font-bold hover:bg-blue-600 transition">
                S'inscrire
            </button>
        </form>

        <div class="text-center mt-4">
            <p class="text-sm">
                Déjà un compte ? <a href="index.jsp" class="text-blue-500 hover:underline">Se connecter</a>
            </p>
        </div>
    </div>

    <script>
        lucide.createIcons();
    </script>
</body>
</html>

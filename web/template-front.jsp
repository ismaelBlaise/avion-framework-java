<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Office - Aéroport</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    
    <header class="bg-white shadow-md fixed top-0 w-full flex items-center justify-between px-6 py-4">
        <div class="flex items-center space-x-4">
            <span class="text-xl font-semibold">Aeroport</span>
            <button class="text-gray-700 text-2xl">&#9776;</button>
        </div>
        <div class="flex items-center space-x-4">
            <img src="assets/img/profile-img.jpg" alt="Profile" class="w-10 h-10 rounded-full">
            <div class="relative">
                <button class="text-gray-700 font-medium">K. Anderson ▼</button>
                <ul class="absolute right-0 mt-2 w-48 bg-white border rounded shadow-md hidden group-hover:block">
                    <li class="px-4 py-2 hover:bg-gray-200 cursor-pointer">Déconnexion</li>
                </ul>
            </div>
        </div>
    </header>
    
    <aside class="bg-white w-64 fixed top-0 left-0 h-full shadow-md pt-16">
        <nav class="px-4 py-6">
            <ul class="space-y-4">
                <li>
                    <button class="flex justify-between w-full text-gray-700 hover:bg-gray-200 p-2 rounded">
                        <span class="flex items-center"><i class="bi bi-journal-text mr-2"></i>Vols</span>
                        <i class="bi bi-chevron-down"></i>
                    </button>
                    <ul class="pl-6 mt-2 space-y-2 hidden">
                        <li><a href="#" class="block text-gray-600 hover:text-gray-900">Avions</a></li>
                        <li><a href="#" class="block text-gray-600 hover:text-gray-900">Classe</a></li>
                        <li><a href="#" class="block text-gray-600 hover:text-gray-900">Villes</a></li>
                        <li><a href="#" class="block text-gray-600 hover:text-gray-900">Statuts</a></li>
                        <li><a href="#" class="block text-gray-600 hover:text-gray-900">Recherche</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </aside>
    
    <main class="ml-64 pt-20 p-6">
        <h1 class="text-2xl font-bold">Tableau de bord</h1>
    </main>
</body>
</html>

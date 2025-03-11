<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Front Office - Aeroport</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const menuButton = document.querySelector('button');
            const sidebar = document.querySelector('aside');
            const dropdownMenu = document.querySelector('.dropdown-menu');
            const profileButton = document.querySelector('button[aria-haspopup="true"]');
            const subMenuButton = document.querySelector('.submenu-toggle');
            const subMenu = document.querySelector('.submenu');

            // Toggle Sidebar
            menuButton.addEventListener('click', function() {
                sidebar.classList.toggle('hidden');
            });

            // Toggle dropdown menu pour le profil
            profileButton.addEventListener('click', function() {
                dropdownMenu.classList.toggle('hidden');
            });

            // Toggle the sub-menu visibility when the "Vols" button is clicked
            subMenuButton.addEventListener('click', function() {
                subMenu.classList.toggle('hidden');
            });
        });
    </script>
</head>
<body class="bg-gray-100 font-sans">

    <header class="bg-white shadow-md fixed top-0 left-0 w-full flex items-center justify-between px-6 py-4 z-10">
        <div class="flex items-center space-x-4">
            <button class="text-gray-700 text-3xl lg:hidden" aria-label="Open Sidebar">&#9776;</button>
            <span class="text-xl font-semibold text-gray-700" style="color:blue ;">Aeroport</span>
        </div>
        <div class="flex items-center space-x-4">
            <img src="https://www.w3schools.com/w3images/avatar2.png" alt="Profile" class="w-10 h-10 rounded-full">

            <div class="relative">
                <button class="text-gray-700 font-medium" aria-haspopup="true">K. Anderson </button>
                <ul class="dropdown-menu absolute right-0 mt-2 w-48 bg-white border rounded shadow-md hidden">
                    <li><a href="#" class="block text-gray-600 hover:bg-gray-200 px-4 py-2 cursor-pointer">Deconnexion</a></li>
                </ul>
            </div>
        </div>
    </header>

    <!-- Sidebar (à droite) -->
    <aside class="bg-white w-64 fixed top-0 left-0 h-full shadow-md pt-16 lg:block hidden transition-all duration-300">
        <nav class="px-4 py-6">
            <ul class="space-y-4">
                <li>
                    <button class="flex justify-between w-full text-gray-700 hover:bg-gray-200 p-2 rounded submenu-toggle">
                        <span class="flex items-center"><i class="bi bi-journal-text mr-2"></i> Vols</span>
                        <i class="bi bi-chevron-down"></i>
                    </button>
                    <ul class="pl-6 mt-2 space-y-2 hidden submenu">
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

    <main class="ml-64 pt-20 p-6 lg:ml-0">
        <!-- <h1 class="text-2xl font-bold text-gray-800">Tableau de bord</h1> -->
    </main>

</body>
</html>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Front Office - AeroPlan</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        .sidebar-collapsed {
            width: 5rem;
        }
        .sidebar-expanded {
            width: 16rem;
        }
        .main-content-collapsed {
            margin-left: 5rem;
        }
        .main-content-expanded {
            margin-left: 16rem;
        }
        .nav-item-active {
            background-color: rgba(59, 130, 246, 0.1);
            border-left: 4px solid #3B82F6;
            color: #3B82F6;
        }
        .submenu-active {
            background-color: rgba(243, 244, 246, 0.5);
        }
        .bg-aviation-gradient {
            background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);
        }
        .sidebar-hover:hover {
            background-color: rgba(59, 130, 246, 0.05);
        }
        .submenu-item {
            color: #4B5563;
        }
        .submenu-item:hover {
            background-color: rgba(59, 130, 246, 0.08);
            color: #1E40AF;
        }
    </style>
</head>
<body class="bg-gray-50 font-sans antialiased">
    <!-- Header -->
    <header class="bg-white shadow-sm fixed top-0 left-0 right-0 flex items-center justify-between px-6 py-3 z-20 h-16">
        <div class="flex items-center space-x-4">
            <!-- Menu Toggle -->
            <button id="menu-button" class="text-gray-600 hover:text-blue-600 transition-colors">
                <i data-lucide="menu" class="w-6 h-6"></i>
            </button>
            
            <!-- Logo -->
            <div class="flex items-center">
                <i data-lucide="plane" class="w-8 h-8 text-blue-600"></i>
                <span class="ml-2 text-xl font-bold text-gray-800 hidden md:block">AeroPlan</span>
                <span class="ml-2 text-sm font-medium text-blue-600 hidden lg:block">Front Office</span>
            </div>
        </div>
        
        <!-- User Profile -->
        <div class="flex items-center space-x-4">
            <div class="relative">
                <button id="profile-button" class="flex items-center space-x-2 focus:outline-none group">
                    <div class="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center group-hover:bg-blue-200 transition-colors">
                        <i data-lucide="user" class="w-4 h-4 text-blue-600"></i>
                    </div>
                    <span class="hidden md:inline text-gray-700 font-medium">B. Ismael</span>
                    <i data-lucide="chevron-down" class="w-4 h-4 text-gray-500 hidden md:block transition-transform group-hover:text-blue-600"></i>
                </button>
                
                <!-- Dropdown Menu -->
                <div id="dropdown-menu" class="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 hidden z-30 border border-gray-200 divide-y divide-gray-100">
                    <div class="px-4 py-2 text-xs text-gray-500">Connecté en tant que</div>
                    <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 flex items-center transition-colors">
                        <i data-lucide="user" class="w-4 h-4 mr-2 text-blue-500"></i> Mon compte
                    </a>
                    <a href="deconnexion" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 flex items-center transition-colors">
                        <i data-lucide="log-out" class="w-4 h-4 mr-2 text-blue-500"></i> Déconnexion
                    </a>
                </div>
            </div>
        </div>
    </header>

    <!-- Sidebar -->
    <aside id="sidebar" class="bg-white shadow-sm fixed top-16 left-0 bottom-0 sidebar-expanded transition-all duration-300 z-10 overflow-y-auto">
        <nav class="px-2 py-6">
            <ul class="space-y-1">
                <li>
                    <button class="submenu-toggle flex items-center justify-between w-full px-3 py-2 text-gray-700 hover:bg-blue-50 rounded-lg transition-colors sidebar-hover">
                        <div class="flex items-center">
                            <i data-lucide="plane" class="w-5 h-5 mr-3 text-blue-500"></i>
                            <span class="font-medium">Gestion des Vols</span>
                        </div>
                        <i data-lucide="chevron-down" class="w-4 h-4 transform transition-transform text-gray-400"></i>
                    </button>
                    <ul id="vols-submenu" class="pl-8 mt-1 space-y-1 hidden">
                        <li>
                            <a href="vols-disponible" class="block px-3 py-2 rounded-lg flex items-center submenu-item transition-colors">
                                <i data-lucide="calendar" class="w-4 h-4 mr-3 text-blue-400"></i>
                                <span>Vols disponibles</span>
                            </a>
                        </li>
                        <li>
                            <a href="reservations" class="block px-3 py-2 rounded-lg flex items-center submenu-item transition-colors">
                                <i data-lucide="ticket" class="w-4 h-4 mr-3 text-blue-400"></i>
                                <span>Mes réservations</span>
                            </a>
                        </li>
                        <li>
                            <a href="vols-recherche-front-form" class="block px-3 py-2 rounded-lg flex items-center submenu-item transition-colors">
                                <i data-lucide="search" class="w-4 h-4 mr-3 text-blue-400"></i>
                                <span>Recherche de vols</span>
                            </a>
                        </li>
                    </ul>
                </li>
                
                
                
               
            </ul>
        </nav>
    </aside>

    <!-- Main Content -->
    <main id="main-content" class="main-content-expanded pt-16 px-6 transition-all duration-300">
        <div class="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
            <jsp:include page="<%= (request.getAttribute(\"page\") != null) ? request.getAttribute(\"page\").toString() : \"accueil.jsp\" %>" />
        </div>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Initialize Lucide icons
            lucide.createIcons();
            
            // DOM Elements
            const menuButton = document.getElementById('menu-button');
            const sidebar = document.getElementById('sidebar');
            const mainContent = document.getElementById('main-content');
            const profileButton = document.getElementById('profile-button');
            const dropdownMenu = document.getElementById('dropdown-menu');
            const submenuToggle = document.querySelector('.submenu-toggle');
            const submenu = document.getElementById('vols-submenu');
            
            // Toggle Sidebar
            menuButton.addEventListener('click', function() {
                sidebar.classList.toggle('sidebar-collapsed');
                sidebar.classList.toggle('sidebar-expanded');
                mainContent.classList.toggle('main-content-collapsed');
                mainContent.classList.toggle('main-content-expanded');
                
                // Rotate chevron icon
                const chevron = this.querySelector('i');
                if (chevron) {
                    chevron.classList.toggle('rotate-180');
                }
            });
            
            // Toggle Profile Dropdown
            profileButton.addEventListener('click', function(e) {
                e.stopPropagation();
                dropdownMenu.classList.toggle('hidden');
            });
            
            // Toggle Submenu
            if (submenuToggle) {
                submenuToggle.addEventListener('click', function() {
                    submenu.classList.toggle('hidden');
                    const chevron = this.querySelector('i:last-child');
                    chevron.classList.toggle('rotate-180');
                });
            }
            
            // Close dropdown when clicking outside
            document.addEventListener('click', function(e) {
                if (!profileButton.contains(e.target) && !dropdownMenu.contains(e.target)) {
                    dropdownMenu.classList.add('hidden');
                }
            });
            
            // Tooltip for collapsed sidebar items
            if (sidebar.classList.contains('sidebar-collapsed')) {
                const tooltipItems = document.querySelectorAll('.sidebar-collapsed a[title]');
                tooltipItems.forEach(item => {
                    item.addEventListener('mouseenter', function(e) {
                        const title = this.getAttribute('title');
                        const tooltip = document.createElement('div');
                        tooltip.className = 'absolute left-full ml-2 px-3 py-2 bg-gray-800 text-white text-sm rounded whitespace-nowrap z-50 shadow-lg';
                        tooltip.textContent = title;
                        this.appendChild(tooltip);
                        
                        this.addEventListener('mouseleave', function() {
                            tooltip.remove();
                        });
                    });
                });
            }
        });
    </script>
</body>
</html>
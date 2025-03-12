<div class="py-6">
    <!-- Bouton Ajouter un avion -->
    <div class="mb-4">
        <button class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
            Ajouter un avion
        </button>
    </div>

    <!-- Tableau des avions -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
            <thead>
                <tr class="text-left bg-gray-100 text-sm font-semibold text-gray-700">
                    <th class="px-6 py-4 border-b">ID Avion</th>
                    <th class="px-6 py-4 border-b">Capacité</th>
                    <th class="px-6 py-4 border-b">Modèle</th>
                    <th class="px-6 py-4 border-b">Actions</th>
                </tr>
            </thead>
            <tbody class="text-sm text-gray-700">
                <!-- Exemple d'itération sur des données d'avions -->
                <tr class="hover:bg-gray-50">
                    <td class="px-6 py-4 border-b">1</td>
                    <td class="px-6 py-4 border-b">150</td>
                    <td class="px-6 py-4 border-b">Airbus A320</td>
                    <td class="px-6 py-4 border-b flex space-x-2">
                        <button class="text-blue-500 hover:text-blue-700">Modifier</button>
                        <button class="text-red-500 hover:text-red-700">Supprimer</button>
                    </td>
                </tr>
                <!-- Répéter pour chaque avion -->
                <tr class="hover:bg-gray-50">
                    <td class="px-6 py-4 border-b">2</td>
                    <td class="px-6 py-4 border-b">200</td>
                    <td class="px-6 py-4 border-b">Boeing 737</td>
                    <td class="px-6 py-4 border-b flex space-x-2">
                        <button class="text-blue-500 hover:text-blue-700">Modifier</button>
                        <button class="text-red-500 hover:text-red-700">Supprimer</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

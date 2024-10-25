import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getAnimalById, updateAnimal, AnimalDto } from '../api/animalApi';

const UpdateAnimalForm: React.FC = () => {
    const { id } = useParams<{ id: string }>();  // Captura o ID do animal da URL
    const [animal, setAnimal] = useState<AnimalDto>({
        name: '',
        description: '',
        category: 'DOG',
        status: 'AVAILABLE'
    });

    useEffect(() => {
        if (id) {
            const loadAnimal = async () => {
                const data = await getAnimalById(parseInt(id)); // Certifique-se de converter o ID para número
                setAnimal(data);
            };
            loadAnimal();
        }
    }, [id]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (id) {
            await updateAnimal(parseInt(id), animal); // Converte o ID para número antes de usá-lo
            alert('Animal updated successfully!');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Update Animal</h2>
            <input
                type="text"
                value={animal.name}
                onChange={(e) => setAnimal({ ...animal, name: e.target.value })}
                required
            />
            <input
                type="text"
                value={animal.description}
                onChange={(e) => setAnimal({ ...animal, description: e.target.value })}
                required
            />
            <select value={animal.category} onChange={(e) => setAnimal({ ...animal, category: e.target.value as 'DOG' | 'CAT' })}>
                <option value="DOG">Dog</option>
                <option value="CAT">Cat</option>
            </select>
            <select value={animal.status} onChange={(e) => setAnimal({ ...animal, status: e.target.value as 'AVAILABLE' | 'ADOPTED' })}>
                <option value="AVAILABLE">Available</option>
                <option value="ADOPTED">Adopted</option>
            </select>
            <button type="submit">Update</button>
        </form>
    );
};

export default UpdateAnimalForm;

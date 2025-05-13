import { Router } from 'express';
import { PrismaClient } from '@prisma/client';

const router = Router();
const prisma = new PrismaClient();

// List all todos
router.get('/', async (_req, res) => {
  try {
    const user_id = (_req as any).user.id;
    const notes = await prisma.todos.findMany(
      user_id ? { where: { user_id } } : undefined
    );
    res.json(notes);
  } catch (err) {
    res.status(500).json({ error: (err as Error).message });
  }
});

// Create a new todo
router.post('/', async (_req, res) => {
  const { title } = _req.body;
  const user_id = (_req as any).user.id;
  try {
    const newTodo = await prisma.todos.create({
      data: { title, user_id },
    });
    res.status(201).json(newTodo);
  } catch (error) {
    res.status(500).json({ error: 'Failed to create todo' });
  }
});

// Update todo
router.put('/:id/', async (_req, res) => {
  const { id } = _req.params;
  const { title } = _req.body;
  try {
    const updatedTodo = await prisma.todos.update({
      where: { id: BigInt(id) },
      data: { title },
    });
    res.status(200).json(updatedTodo);
  } catch (error) {
    res.status(500).json({ error: 'Failed to update todo' });
  }
});

// Delete todo
router.delete('/:id/', async (_req, res) => {
  const { id } = _req.params;
  try {
    await prisma.todos.delete({
      where: { id: BigInt(id) },
    });
    res.status(204).send();
  } catch (error) {
    res.status(500).json({ error: 'Failed to delete todo' });
  }
});

export default router;

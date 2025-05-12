import { Router } from 'express';
import { PrismaClient } from '@prisma/client';

const router = Router();
const prisma = new PrismaClient();

router.get('/', async (_req, res) => {
  try {
    const notes = await prisma.notes.findMany();
    res.json(notes);
  } catch (err) {
    res.status(500).json({ error: (err as Error).message });
  }
});

export default router;

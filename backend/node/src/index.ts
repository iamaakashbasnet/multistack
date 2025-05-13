import express from 'express';
import notesRouter from './routes/notes';
import { verifyToken } from './middlewares/jwtMiddleware';

(BigInt.prototype as any).toJSON = function () {
  return this.toString();
};

const app = express();
app.use(verifyToken);

app.use(express.json());
app.use('/api/notes/', notesRouter);

const PORT = process.env.PORT || 8002;
app.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}`);
});

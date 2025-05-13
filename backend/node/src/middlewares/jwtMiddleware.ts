import { Request, Response, NextFunction, RequestHandler } from 'express';
import jwt, { JwtPayload } from 'jsonwebtoken';

const JWT_SECRET =
  'django-insecure-hgb_kwmtl#wiyuk&23fzqt$*fk_dtt1oqc$e8=)3b$y@alqaap';

interface AuthenticatedRequest extends Request {
  user?: {
    id: number;
    username: string;
  };
}

export const verifyToken = (
  req: AuthenticatedRequest,
  res: Response,
  next: NextFunction
): void => {
  const authHeader = req.headers.authorization;

  if (!authHeader || !authHeader.startsWith('Bearer ')) {
    res.status(401).json({ message: 'Missing or invalid token' });
    return;
  }

  const token = authHeader.split(' ')[1];

  try {
    const decoded = jwt.verify(token, JWT_SECRET) as JwtPayload;

    req.user = {
      id: decoded.user_id,
      username: decoded.username,
    };

    next();
  } catch (error) {
    res.status(401).json({ message: 'Invalid token' });
  }
};

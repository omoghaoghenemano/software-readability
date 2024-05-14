public Point getClosestPoint(Point anotherPt) {
        Rectangle r = getBounds();
        int[] xs = {r.x + r.width / 2,
                    r.x + r.width,
                    r.x + r.width / 2,
                    r.x,
                    r.x + r.width / 2,
        };
        int[] ys = {r.y,
                    r.y + r.height / 2,
                    r.y + r.height,
                    r.y + r.height / 2,
                    r.y,
        };
        Point p =
            Geometry.ptClosestTo(
                xs,
                ys,
                5,
                anotherPt);
        return p;
    }
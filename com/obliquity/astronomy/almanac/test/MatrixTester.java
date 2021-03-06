/*
 * astrojava - a package for reading JPL ephemeris files
 *
 * Copyright (C) 2006-2014 David Harper at obliquity.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 *
 * See the COPYING file located in the top-level-directory of
 * the archive of this library for complete text of license.
 */

package com.obliquity.astronomy.almanac.test;

import java.text.DecimalFormat;

import com.obliquity.astronomy.almanac.Matrix;
import com.obliquity.astronomy.almanac.Vector;

public class MatrixTester {
	public static void main(String args[]) {
		DecimalFormat format = new DecimalFormat("0.000000");
		format.setPositivePrefix(" ");
		Matrix a = new Matrix();

		System.out.println("a = new Matrix() =>\n" + a.prettyPrint(format));

		System.out.println();

		a = Matrix.getIdentityMatrix();

		System.out.println("a = Matrix.getIdentityMatrix() =>\n"
				+ a.prettyPrint(format));

		System.out.println("det(a) => " + a.determinant());

		System.out.println();

		double angle = Math.PI / 3.0;

		Matrix ax = Matrix.getRotationMatrix(Matrix.X_AXIS, angle);
		System.out.println("ax = Matrix.getRotationMatrix(X_AXIS, "
				+ format.format(angle) + ") =>\n" + ax.prettyPrint(format));
		System.out.println("det(ax) => " + ax.determinant());

		System.out.println();

		Matrix ay = Matrix.getRotationMatrix(Matrix.Y_AXIS, angle);
		System.out.println("ay = Matrix.getRotationMatrix(Y_AXIS, "
				+ format.format(angle) + ") =>\n" + ay.prettyPrint(format));
		System.out.println("det(ay) => " + ay.determinant());

		System.out.println();

		Matrix az = Matrix.getRotationMatrix(Matrix.Z_AXIS, angle);
		System.out.println("az = Matrix.getRotationMatrix(Z_AXIS, "
				+ format.format(angle) + ") =>\n" + az.prettyPrint(format));
		System.out.println("det(ax) => " + az.determinant());

		System.out.println();

		System.out.println("Testing rightMultiplyBy");

		a = new Matrix(ax);

		a.rightMultiplyBy(ay);
		a.rightMultiplyBy(az);

		System.out.println("ax x ay x az =>\n" + a.prettyPrint(format));

		System.out.println("det(ax . ay . az) => " + a.determinant());

		System.out.println();

		System.out.println("testing leftMultiplyBy");

		a = new Matrix(az);

		a.leftMultiplyBy(ay);
		a.leftMultiplyBy(ax);

		System.out.println("ax x ay x az =>\n" + a.prettyPrint(format));

		System.out.println("det(ax . ay . az) => " + a.determinant());

		System.out.println();

		System.out.println("Testing matrix-vector multiplication");

		Vector v = new Vector(1.0, 2.0, 3.0);

		System.out.println("v=" + v.prettyPrint(format));

		System.out.println("mag(v) => " + v.magnitude());

		v.multiplyBy(a);

		System.out.println("Rotated v=" + v.prettyPrint(format));

		System.out.println("mag(v) => " + v.magnitude());

		System.out.println();

		System.out.println("Testing matrix transpose");

		System.out.println("Before:\n" + a.prettyPrint(format) + "\n");

		a.transpose();

		System.out.println("After:\n" + a.prettyPrint(format) + "\n");
	}
}

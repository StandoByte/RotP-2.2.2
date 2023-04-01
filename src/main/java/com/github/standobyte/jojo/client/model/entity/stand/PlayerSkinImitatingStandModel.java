package com.github.standobyte.jojo.client.model.entity.stand;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.util.reflection.ClientReflection;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Util;

public class PlayerSkinImitatingStandModel<T extends StandEntity> extends HumanoidStandModel<T> {
    private final boolean slim; 

    public PlayerSkinImitatingStandModel(boolean slim) {
        super(64, 64);
        this.slim = slim;

        this.head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0);
        this.head.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);
        
        this.torso.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0);
        this.torso.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F);
        
        if (slim) {
            leftArm.setPos(5.5F, -9.5F, 0.0F);
            ClientReflection.setCubes(leftArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        32, 48, -1.5F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        48, 48, -1.5F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
            }));

            leftForeArm.setPos(0.0F, 4.0F, 0.0F);
            ClientReflection.setCubes(leftForeArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        32, 54, -1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        48, 54, -1.5F, 0.25F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
            }));


            rightArm.setPos(-5.5F, -9.5F, 0.0F);
            ClientReflection.setCubes(rightArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 16, -1.5F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 32, -1.5F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
            }));

            rightForeArm.setPos(-0.0F, 4.0F, 0.0F);
            ClientReflection.setCubes(rightForeArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 22, -1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 38, -1.5F, 0.25F, -2.0F, 3.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
            }));
        }

        else {
            leftArm.setPos(6.0F, -10.0F, 0.0F);
            ClientReflection.setCubes(leftArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        32, 48, -2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        48, 48, -2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
            }));

            leftForeArm.setPos(0.0F, 4.0F, 0.0F);
            ClientReflection.setCubes(leftForeArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        32, 54, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        48, 54, -2.0F, 0.25F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
            }));


            rightArm.setPos(-6.0F, -10.0F, 0.0F);
            ClientReflection.setCubes(rightArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 16, -2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 32, -2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
            }));

            rightForeArm.setPos(-0.0F, 4.0F, 0.0F);
            ClientReflection.setCubes(rightForeArm, Util.make(new ObjectArrayList<>(), list -> { 
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 22, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
                list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                        40, 38, -2.0F, 0.25F, -2.0F, 4.0F, 6.0F, 4.0F, 
                        0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
            }));
        }

        leftLeg.setPos(1.9F, 12.0F, 0.0F);
        ClientReflection.setCubes(leftLeg, Util.make(new ObjectArrayList<>(), list -> { 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    16, 48, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 48, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
        }));

        leftLowerLeg.setPos(0.0F, 6.0F, 0.0F);
        ClientReflection.setCubes(leftLowerLeg, Util.make(new ObjectArrayList<>(), list -> { 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    16, 54, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 54, -2.0F, 0.25F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
        }));


        rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        ClientReflection.setCubes(rightLeg, Util.make(new ObjectArrayList<>(), list -> { 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0, 0, 0, false, texWidth, texHeight), LimbPart.UPPER));
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 32, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F,
                    0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.UPPER));
        }));

        rightLowerLeg.setPos(0.0F, 6.0F, 0.0F);
        ClientReflection.setCubes(rightLowerLeg, Util.make(new ObjectArrayList<>(), list -> { 
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 22, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 
                    0, 0, 0, false, texWidth, texHeight), LimbPart.LOWER));
            list.add(fixLimbPolygons(new ModelRenderer.ModelBox(
                    0, 38, -2.0F, 0.25F, -2.0F, 4.0F, 6.0F, 4.0F,
                    0.25F, 0.25F, 0.25F, false, texWidth, texHeight), LimbPart.LOWER));
        }));
    }
    
    
    
    private ModelRenderer.ModelBox fixLimbPolygons(ModelRenderer.ModelBox box, LimbPart part) {
        ModelRenderer.TexturedQuad[] polygons = ClientReflection.getPolygons(box);
        switch (part) {
        case UPPER:
            ClientReflection.setPolygons(box, new ModelRenderer.TexturedQuad[] {
                    polygons[0],
                    polygons[1],
                    polygons[2],
                    polygons[4],
                    polygons[5]
            });
            break;
        case LOWER:
            shiftRemap(polygons[3], 0, -6);
            ClientReflection.setPolygons(box, new ModelRenderer.TexturedQuad[] {
                    polygons[0],
                    polygons[1],
                    polygons[3],
                    polygons[4],
                    polygons[5]
            });
            break;
        }
        return box;
    }
    
    private void shiftRemap(ModelRenderer.TexturedQuad quad, float texXShift, float texYShift) {
        float uShift = texXShift / texWidth;
        float vShift = texYShift / texHeight;
        for (int i = 0; i < quad.vertices.length; i++) {
            quad.vertices[i] = shiftRemap(quad.vertices[i], uShift, vShift);
        }
    }
    
    private ModelRenderer.PositionTextureVertex shiftRemap(ModelRenderer.PositionTextureVertex vertex, float uShift, float vShift) {
        return vertex.remap(vertex.u + uShift, vertex.v + vShift);
    }
    
    private static enum LimbPart {
        UPPER,
        LOWER;
    }
}
